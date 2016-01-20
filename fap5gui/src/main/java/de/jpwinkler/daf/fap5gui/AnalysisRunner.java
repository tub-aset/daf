package de.jpwinkler.daf.fap5gui;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.WorkflowException;
import de.jpwinkler.daf.dafcore.workflow.WorkflowProcessor;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5.model.codebeamer.IntMetric;
import de.jpwinkler.daf.fap5.model.codebeamer.Issue;
import de.jpwinkler.daf.fap5.model.codebeamer.Metric;
import de.jpwinkler.daf.fap5gui.gui.ProgressMonitor;
import de.jpwinkler.daf.fap5gui.model.AnalysisResults;
import de.jpwinkler.daf.fap5gui.model.AnalysisSettings;
import de.jpwinkler.daf.fap5gui.model.DocumentSnapshot;
import de.jpwinkler.daf.fap5gui.model.Version;
import de.jpwinkler.daf.workflowdsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.Step;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Variable;
import de.jpwinkler.daf.workflowdsl.Workflow;

public class AnalysisRunner {

    private static final Logger LOGGER = Logger.getLogger(AnalysisRunner.class.getName());

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyyMMdd");

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private AnalysisResults results;
    private AnalysisSettings settings;

    public AnalysisResults run(final File resultCacheFile, final AnalysisSettings settings, final ProgressMonitor progressMonitor) throws JsonSyntaxException, IOException {

        this.settings = settings;

        if (progressMonitor != null) {
            progressMonitor.updateProgres(0, "Warming up");
        }

        if (resultCacheFile != null && resultCacheFile.exists() && settings.isReuseCache()) {
            results = gson.fromJson(FileUtils.readFileToString(resultCacheFile), AnalysisResults.class);
        } else {
            results = new AnalysisResults();
        }

        final File[] listFiles = new File(settings.getCsvDirectory()).listFiles();
        int i = 0;
        for (final File child : listFiles) {
            if (child.isDirectory()) {
                if (!results.getVersionNumbers().contains(child.getName())) {
                    if (progressMonitor != null) {
                        progressMonitor.updateProgres((double) i / (double) listFiles.length, "Processing " + child.getName());
                    }
                    processExistingVersionFolder(child.getName());
                }
            }

            i++;
        }

        if (settings.isRunNewDoorsAnalysis() || settings.isGenerateIssueLists() || settings.isUpdateProgressReportExcelSheet()) {
            processNewVersion();
        }

        if (resultCacheFile != null) {
            FileUtils.write(resultCacheFile, gson.toJson(results));
        }

        return results;
    }

    private void processNewVersion() {
        final String version = DATE_FORMAT.format(new Date());

        final Workflow workflowModel = new Workflow();

        final ModuleSet moduleSet = new ModuleSet();
        final ModuleSetEntry moduleSetEntry = new ModuleSetEntry();
        moduleSetEntry.setType("csvfolder");
        moduleSetEntry.setReference(new File(settings.getCsvDirectory(), version).getAbsolutePath());
        moduleSet.getModuleSetEntries().add(moduleSetEntry);
        workflowModel.getElements().add(moduleSet);

        final ModelConstructorStep codeBeamerModelConstructorStep = new ModelConstructorStep();
        codeBeamerModelConstructorStep.setName("CodeBeamerModelConstructor");
        codeBeamerModelConstructorStep.getFeatures().add(createSourceFeature(moduleSet));
        codeBeamerModelConstructorStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"));
        workflowModel.getElements().add(codeBeamerModelConstructorStep);
        final Target codeBeamerModelConstructorTarget = createTarget(codeBeamerModelConstructorStep);
        workflowModel.getElements().add(codeBeamerModelConstructorTarget);

        if (settings.isRunNewDoorsAnalysis()) {
            final ModelOperationStep doorsExportStep = new ModelOperationStep();
            doorsExportStep.setName("ExportModules");
            doorsExportStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.ExportModulesOp"));

            codeBeamerModelConstructorStep.getFeatures().add(createDependencyFeature("exportModules", doorsExportStep));
            workflowModel.getElements().add(createSimpleVariable("database", settings.getDoorsDatabase()));
            workflowModel.getElements().add(createSimpleVariable("username", settings.getDoorsUsername()));
            workflowModel.getElements().add(createSimpleVariable("password", settings.getDoorsPassword()));
            workflowModel.getElements().add(createSimpleVariable("exportFolder", new File(settings.getCsvDirectory(), version).getAbsolutePath()));
            workflowModel.getElements().add(createSimpleVariable("moduleList", settings.getModuleListFile()));
        }

        if (settings.isUpdateProgressReportExcelSheet()) {
            final ModelOperationStep updateProgressReportStep = new ModelOperationStep();
            updateProgressReportStep.setName("UpdateExcelOp");
            updateProgressReportStep.getFeatures().add(createDependencyFeature("codeBeamerModels", codeBeamerModelConstructorStep));
            updateProgressReportStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.UpdateExcelOp"));
            workflowModel.getElements().add(createTarget(updateProgressReportStep));
            workflowModel.getElements().add(createSimpleVariable("sourceExcelFile", settings.getProgressReportSourceFile()));
            workflowModel.getElements().add(createSimpleVariable("targetExcelFile", settings.getProgressReportTargetFile()));
        }

        if (settings.isGenerateIssueLists()) {
            final ModelOperationStep exportIssueListsStep = new ModelOperationStep();
            exportIssueListsStep.setName("ExportIssueListsOp");
            exportIssueListsStep.getFeatures().add(createDependencyFeature("codeBeamerModels", codeBeamerModelConstructorStep));
            exportIssueListsStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.ExportIssueListsOp"));
            workflowModel.getElements().add(createTarget(exportIssueListsStep));
            workflowModel.getElements().add(createSimpleVariable("issueFolder", settings.getIssueListsDirectory()));
        }

        try {
            final Map<Target, List<ModelObject>> workflowResults = new WorkflowProcessor().runWorkflowModel(workflowModel);

            final Version v = createVersion(version, workflowResults.get(codeBeamerModelConstructorTarget));
            results.addVersion(v);
        } catch (final WorkflowException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private Target createTarget(final Step step) {
        final Target target;
        target = new Target();
        target.setStep(step);
        return target;
    }

    private void processExistingVersionFolder(final String versionFolder) {
        final ModuleSet moduleSet = new ModuleSet();
        final ModuleSetEntry moduleSetEntry = new ModuleSetEntry();
        moduleSetEntry.setType("csvfolder");
        moduleSetEntry.setReference(new File(settings.getCsvDirectory(), versionFolder).getAbsolutePath());
        moduleSet.getModuleSetEntries().add(moduleSetEntry);

        final ModelConstructorStep modelConstructorStep = new ModelConstructorStep();
        modelConstructorStep.setName("CodeBeamerModelConstructor");
        modelConstructorStep.getFeatures().add(createSourceFeature(moduleSet));
        modelConstructorStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"));

        final Workflow workflowModel = new Workflow();
        workflowModel.getElements().add(moduleSet);
        workflowModel.getElements().add(modelConstructorStep);

        final Target target = new Target();
        target.setStep(modelConstructorStep);
        workflowModel.getElements().add(target);

        try {
            final Map<Target, List<ModelObject>> workflowResult = new WorkflowProcessor().runWorkflowModel(workflowModel);

            final Version version = createVersion(versionFolder, workflowResult.get(target));

            results.addVersion(version);

        } catch (final WorkflowException e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private Version createVersion(final String versionString, final List<ModelObject> codeBeamerModels) {
        final Version version = new Version();
        version.setVersionString(versionString);
        try {
            version.setDate(DATE_FORMAT.parse(versionString));
        } catch (final ParseException e) {
            throw new RuntimeException(e);
        }

        for (final ModelObject modelObject : codeBeamerModels) {
            final CodeBeamerModel cbm = (CodeBeamerModel) modelObject;

            final DocumentSnapshot snapshot = createDocumentSnapshot(cbm);

            version.getDocumentSnapshots().put(snapshot.getDocumentName(), snapshot);
        }
        return version;
    }

    private DocumentSnapshot createDocumentSnapshot(final CodeBeamerModel cbm) {
        final DocumentSnapshot snapshot = new DocumentSnapshot();
        snapshot.setDocumentName(cbm.getName());

        for (final Metric metric : cbm.getMetrics()) {
            if (metric instanceof IntMetric) {
                snapshot.setMetric(metric.getName(), ((IntMetric) metric).getValue());
            }
        }

        for (final Issue issue : cbm.getIssues()) {
            final de.jpwinkler.daf.fap5gui.model.Issue i = new de.jpwinkler.daf.fap5gui.model.Issue();
            i.setIssueMessage(issue.getIssueType());
            i.setObjectId(issue.getSource().getObjectIdentifier());
            i.setObjectNumber(issue.getSource().getObjectNumber());
            i.setObjectAbsoluteNumber(issue.getSource().getAbsoluteNumber());
            i.setAbsoluteModuleName(cbm.getFullName());
            snapshot.getIssues().add(i);
        }

        return snapshot;
    }

    private SourceFeature createSourceFeature(final ModuleSet moduleSet) {
        final SourceFeature sourceFeature = new SourceFeature();
        sourceFeature.setModuleSet(moduleSet);
        return sourceFeature;
    }

    private ImplementationFeature createImplementationFeature(final String implName) {
        final ImplementationFeature implementationFeature = new ImplementationFeature();
        implementationFeature.setImplementation(implName);
        return implementationFeature;
    }

    private DependencyFeature createDependencyFeature(final String name, final Step step, final Variable... variables) {
        final DependencyFeature dependencyFeature = new DependencyFeature();
        dependencyFeature.setName(name);
        dependencyFeature.setStep(step);
        for (final Variable v : variables) {
            dependencyFeature.getVariables().add(v);
        }
        return dependencyFeature;
    }

    private Variable createSimpleVariable(final String name, final String value) {
        final SimpleVariable variable = new SimpleVariable();
        variable.setName(name);
        variable.setValue(value);

        return variable;
    }
}
