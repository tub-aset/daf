package de.jpwinkler.daf.dafcore.workflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.apache.commons.io.FilenameUtils;

import de.jpwinkler.daf.dafcore.csv.ModuleCSVParser;
import de.jpwinkler.daf.dafcore.csv.ModuleMetaDataParser;
import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util.CSVParseException;
import de.jpwinkler.daf.dafcore.util.CsvFolderIterator;
import de.jpwinkler.daf.workflowdsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.OperationFeature;
import de.jpwinkler.daf.workflowdsl.Step;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Variable;
import de.jpwinkler.daf.workflowdsl.Workflow;
import de.jpwinkler.daf.workflowdsl.factory.CreateWorkflowException;
import de.jpwinkler.daf.workflowdsl.factory.WorkflowFactory;
import de.jpwinkler.libs.doorsbridge.DoorsApplication;
import de.jpwinkler.libs.doorsbridge.DoorsApplicationFactory;
import de.jpwinkler.libs.doorsbridge.DoorsException;
import de.jpwinkler.libs.doorsbridge.DoorsURL;

public class WorkflowProcessor {

    private static final Logger LOGGER = Logger.getLogger(WorkflowProcessor.class.getName());

    private final ResultCache resultCache = new ResultCache();

    private final DoorsApplication doorsApp;

    public WorkflowProcessor() {
        doorsApp = DoorsApplicationFactory.getDoorsApplication();
    }

    public void runWorkflow(final InputStream workflowFileStream) throws WorkflowException {
        Workflow workflowModel;

        try {
            workflowModel = new WorkflowFactory().createWorkflow(workflowFileStream);
        } catch (IOException | CreateWorkflowException e) {
            throw new WorkflowException(e);
        }

        runWorkflowModel(workflowModel);
    }

    public void runWorkflow(final File workflowFile) throws WorkflowException {
        LOGGER.info(String.format("Starting workflow execution. Workflow file: %s", workflowFile.getAbsolutePath()));
        Workflow workflowModel;
        try {
            workflowModel = new WorkflowFactory().createWorkflow(new FileInputStream(workflowFile));
        } catch (final IOException | CreateWorkflowException e) {
            throw new WorkflowException("Error while reading workflow file.", e);
        }
        LOGGER.info("Workflow model loaded.");

        runWorkflowModel(workflowModel);
    }

    public Map<Target, List<ModelObject>> runWorkflowModel(final Workflow workflowModel) throws WorkflowException {
        resultCache.clear();
        final long t1 = System.currentTimeMillis();
        try {
            new WorkflowValidator().validate(workflowModel);
        } catch (final WorkflowValidationException e) {
            throw new WorkflowException(e);
        }

        final Map<String, Object> variables = new HashMap<>();

        for (final Variable variable : WorkflowUtil.getVariables(workflowModel)) {
            variables.put(variable.getName(), WorkflowUtil.getVariableValue(variable));
        }

        variables.put("system.date", new SimpleDateFormat("yyyyMMdd").format(new Date()));
        variables.put("system.time", new SimpleDateFormat("HHmmss").format(new Date()));

        final Map<Target, List<ModelObject>> results = new HashMap<Target, List<ModelObject>>();

        for (final Target target : WorkflowUtil.getTargets(workflowModel)) {
            results.put(target, processStep(target.getStep(), variables));
        }
        LOGGER.info(String.format("Workflow execution completed in %d ms.", System.currentTimeMillis() - t1));
        LOGGER.info(String.format("Total Memory: %d", Runtime.getRuntime().totalMemory()));
        return results;
    }

    private List<ModelObject> processStep(final Step step, final Map<String, Object> variables) throws WorkflowException {
        LOGGER.info(String.format("Preparing to execute step %s.", step.getName()));
        final List<ModelObject> cachedResult = resultCache.findResult(step, variables);
        if (cachedResult != null) {
            LOGGER.info("Using cached result.");
            return cachedResult;
        }

        final StepImpl stepImplementation = findImplementation(step);

        for (final OperationFeature feature : step.getFeatures()) {
            if (feature instanceof DependencyFeature) {
                final DependencyFeature dependency = (DependencyFeature) feature;
                processDependency(step, dependency, "", variables, stepImplementation);
            } else if (feature instanceof ForFeature) {
                final ForFeature forFeature = (ForFeature) feature;
                processFor(step, forFeature, variables, stepImplementation);
            }
        }

        for (final Entry<String, Object> e : variables.entrySet()) {
            stepImplementation.setVariable(e.getKey(), WorkflowUtil.replaceVariables(e.getValue(), variables));
        }

        LOGGER.info(String.format("Executing step %s.", step.getName()));
        final List<ModelObject> result = new ArrayList<>();

        if (step instanceof ModelConstructorStep) {
            final ModelConstructorImpl modelConstructorImpl = (ModelConstructorImpl) stepImplementation;
            final ModelConstructorStep modelConstructor = (ModelConstructorStep) step;

            for (final ModuleSetEntry moduleSetEntry : WorkflowUtil.getSource(modelConstructor).getModuleSet().getModuleSetEntries()) {
                final Iterator<DoorsModule> i = loadModulesLazy(moduleSetEntry, variables);
                while (i.hasNext()) {
                    modelConstructorImpl.setSource(i.next());
                    final ModelObject model = modelConstructorImpl.execute();
                    if (model != null) {
                        result.add(model);
                    }
                }
            }
        } else if (step instanceof ModelOperationStep) {
            final ModelOperationImpl modelOperationImpl = (ModelOperationImpl) stepImplementation;
            result.add(modelOperationImpl.execute());
        }

        resultCache.cacheResult(step, variables, result);

        LOGGER.info(String.format("Step %s successfully executed.", step.getName()));

        return result;
    }

    private void processFor(final Step step, final ForFeature forFeature, final Map<String, Object> variables, final StepImpl stepImplementation) throws WorkflowException {
        if (!variables.containsKey(forFeature.getArrayVar())) {
            throw new WorkflowException(String.format("Unknown variable %s.", forFeature.getArrayVar()));
        } else if (!(variables.get(forFeature.getArrayVar()) instanceof String[])) {
            throw new WorkflowException(String.format("%s is not an array.", forFeature.getArrayVar()));
        }

        final Map<String, Object> forVariables = new HashMap<>(variables);

        for (final String e : (String[]) variables.get(forFeature.getArrayVar())) {
            forVariables.put(forFeature.getLoopVar(), e);

            for (final OperationFeature feature : forFeature.getFeatures()) {
                if (feature instanceof DependencyFeature) {
                    final DependencyFeature dependencyFeature = (DependencyFeature) feature;
                    processDependency(step, dependencyFeature, e, forVariables, stepImplementation);
                } else if (feature instanceof ForFeature) {
                    final ForFeature innerForFeature = (ForFeature) feature;
                    processFor(step, innerForFeature, forVariables, stepImplementation);
                }
            }

        }
    }

    private void processDependency(final Step step, final DependencyFeature dependency, final String dependencyNameSuffix, final Map<String, Object> variables, final StepImpl stepImplementation) throws WorkflowException {
        LOGGER.info(String.format("Processing dependency %s of step %s.", dependency.getName(), step.getName()));

        final Map<String, Object> dependencyVariables = new HashMap<>(variables);

        for (final Variable variable : dependency.getVariables()) {
            if (dependencyVariables.containsKey(variable.getName())) {
                LOGGER.warning(String.format("Overriding variable %s", variable.getName(), variable));
            }
            dependencyVariables.put(variable.getName(), WorkflowUtil.getVariableValue(variable));
        }

        final List<ModelObject> result = processStep(dependency.getStep(), dependencyVariables);
        stepImplementation.setParameter(dependency.getName() + dependencyNameSuffix, result);
    }

    private List<DoorsModule> loadModules(final ModuleSetEntry moduleSetEntry, final Map<String, Object> variables) throws WorkflowException {
        final List<DoorsModule> result = new ArrayList<>();

        final String reference = WorkflowUtil.replaceVariables(moduleSetEntry.getReference(), variables);

        switch (moduleSetEntry.getType()) {
        case "csv":
            final File file = new File(reference);
            if (!file.isFile()) {
                throw new WorkflowException(String.format("%s is not a file.", reference));
            }
            LOGGER.info(String.format("Loading csv from file %s.", file.getAbsolutePath()));
            try {
                result.add(loadDoorsModuleFromCSV(file));
            } catch (IOException | CSVParseException e) {
                throw new WorkflowException(String.format("Error while reading CSV file %s.", file.getAbsolutePath()));
            }
            break;
        case "csvfolder":
            final File csvFolder = new File(reference);
            if (!csvFolder.isDirectory()) {
                throw new WorkflowException(String.format("%s is not a folder.", reference));
            }
            LOGGER.info(String.format("Loading csv files from folder %s", csvFolder.getAbsolutePath()));
            result.addAll(parseCSVFilesInFolder(csvFolder));
            break;
        case "doors":
            LOGGER.info(String.format("Retrieving module %s from DOORS.", reference));
            result.add(loadDoorsModule(reference));
            break;
        case "doorsurl":
            LOGGER.info(String.format("Retrieving module %s from DOORS.", reference));
            result.add(loadDoorsModuleFromUrl(new DoorsURL(reference)));
            break;
        default:
            throw new WorkflowException("Cannot handle module set entry of type " + moduleSetEntry.getType());
        }

        new LinkReconstructor().reconstructLinks(result);

        return result;
    }

    private Iterator<DoorsModule> loadModulesLazy(final ModuleSetEntry moduleSetEntry, final Map<String, Object> variables) throws WorkflowException {
        Iterator<DoorsModule> result;
        final List<DoorsModule> modules = new ArrayList<>();

        final String reference = WorkflowUtil.replaceVariables(moduleSetEntry.getReference(), variables);

        switch (moduleSetEntry.getType()) {
        case "csv":
            final File file = new File(reference);
            if (!file.isFile()) {
                throw new WorkflowException(String.format("%s is not a file.", reference));
            }
            LOGGER.info(String.format("Loading csv from file %s.", file.getAbsolutePath()));
            try {
                modules.add(loadDoorsModuleFromCSV(file));
                new LinkReconstructor().reconstructLinks(modules);
                result = modules.iterator();
            } catch (IOException | CSVParseException e) {
                throw new WorkflowException(String.format("Error while reading CSV file %s.", file.getAbsolutePath()));
            }
            break;
        case "csvfolder":
            final File csvFolder = new File(reference);
            if (!csvFolder.isDirectory()) {
                throw new WorkflowException(String.format("%s is not a folder.", reference));
            }
            LOGGER.info(String.format("Loading csv files from folder %s", csvFolder.getAbsolutePath()));
            result = new CsvFolderIterator(csvFolder);
            break;
        case "doors":
            LOGGER.info(String.format("Retrieving module %s from DOORS.", reference));
            modules.add(loadDoorsModule(reference));
            new LinkReconstructor().reconstructLinks(modules);
            result = modules.iterator();
            break;
        case "doorsurl":
            LOGGER.info(String.format("Retrieving module %s from DOORS.", reference));
            modules.add(loadDoorsModuleFromUrl(new DoorsURL(reference)));
            new LinkReconstructor().reconstructLinks(modules);
            result = modules.iterator();
            break;
        default:
            throw new WorkflowException("Cannot handle module set entry of type " + moduleSetEntry.getType());
        }

        return result;
    }

    private DoorsModule loadDoorsModuleFromCSV(final File file) throws IOException, CSVParseException {
        final DoorsModule module = new ModuleCSVParser().parseCSV(file);
        final File metaDataFile = new File(file.getAbsoluteFile() + ".mmd");
        if (metaDataFile.exists()) {
            new ModuleMetaDataParser().parseModuleMetaData(metaDataFile, module);
        }
        return module;
    }

    private DoorsModule loadDoorsModuleFromUrl(final DoorsURL doorsURL) throws WorkflowException {
        try {
            final File tempFile = File.createTempFile("doors_csv", ".csv");
            final File tempMetaFile = new File(tempFile.getAbsolutePath() + ".mmd");
            tempFile.deleteOnExit();
            tempMetaFile.deleteOnExit();
            doorsApp.exportModuleToCSV(doorsURL, tempFile);
            final DoorsModule module = loadDoorsModuleFromCSV(tempFile);
            return module;
        } catch (IOException | DoorsException | CSVParseException e) {
            throw new WorkflowException(String.format("Unable to read DOORS module %s", doorsURL), e);
        }
    }

    private DoorsModule loadDoorsModule(final String reference) throws WorkflowException {
        try {
            final File tempFile = File.createTempFile("doors_csv", ".csv");
            final File tempMetaFile = new File(tempFile.getAbsolutePath() + ".mmd");
            tempFile.deleteOnExit();
            tempMetaFile.deleteOnExit();
            doorsApp.exportModuleToCSV(reference, tempFile);
            final DoorsModule module = loadDoorsModuleFromCSV(tempFile);
            return module;
        } catch (IOException | DoorsException | CSVParseException e) {
            throw new WorkflowException(String.format("Unable to read DOORS module %s", reference), e);
        }
    }

    private List<DoorsModule> parseCSVFilesInFolder(final File csvFolder) throws WorkflowException {
        final List<DoorsModule> result = new ArrayList<>();
        final File[] listFiles = csvFolder.listFiles();
        if (listFiles != null && listFiles.length > 0) {

            for (final File child : listFiles) {
                try {
                    if (child.isFile() && "csv".equals(FilenameUtils.getExtension(child.getAbsolutePath()))) {
                        LOGGER.info(String.format("Parsing module %s", child.getName()));
                        result.add(loadDoorsModuleFromCSV(child));
                    } else if (child.isDirectory()) {
                        result.addAll(parseCSVFilesInFolder(child));
                    }
                } catch (IOException | CSVParseException e) {
                    throw new WorkflowException(String.format("Error while reading CSV file %s.", child.getAbsolutePath()), e);
                }
            }
        } else if (listFiles != null && listFiles.length == 0) {
            LOGGER.warning(String.format("Empty CSV folder: %s", csvFolder));
        } else {
            LOGGER.severe(String.format("Unable to read contents of folder %s", csvFolder));
        }
        return result;
    }

    private StepImpl findImplementation(final Step step) throws WorkflowException {
        final String stepImplementationClassName = WorkflowUtil.getImplementation(step);

        try {
            return (StepImpl) ClassLoader.getSystemClassLoader().loadClass(stepImplementationClassName).newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new WorkflowException(e);
        }

    }

    public static void main(final String[] args) throws WorkflowException {
        if (args.length != 1) {
            System.out.println("No filename given");
        } else {
            new WorkflowProcessor().runWorkflow(new File(args[0]));
        }
    }
}
