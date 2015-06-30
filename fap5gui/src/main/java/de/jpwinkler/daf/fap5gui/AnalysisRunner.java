package de.jpwinkler.daf.fap5gui;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.workflow.WorkflowException;
import de.jpwinkler.daf.dafcore.workflow.WorkflowProcessor;
import de.jpwinkler.daf.fap5.codebeamerrules.CodeBeamerConstants;
import de.jpwinkler.daf.fap5.model.codebeamer.CodeBeamerModel;
import de.jpwinkler.daf.fap5gui.model.AnalysisResults;
import de.jpwinkler.daf.fap5gui.model.AnalysisSettings;
import de.jpwinkler.daf.fap5gui.model.DocumentSnapshot;
import de.jpwinkler.daf.fap5gui.model.Version;
import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSetEntry;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Variable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowDslFactory;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel;

public class AnalysisRunner {

    private final WorkflowDslFactory factory = WorkflowDslFactory.eINSTANCE;
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    private AnalysisResults results;
    private AnalysisSettings settings;

    public AnalysisResults run(final AnalysisSettings settings, final ProgressMonitor progressMonitor) throws JsonSyntaxException, IOException {

        this.settings = settings;

        progressMonitor.updateProgres(0, "Warming up");

        final File file = new File("temp", "temp.json");
        if (file.exists() && settings.isReuseCache()) {
            results = gson.fromJson(FileUtils.readFileToString(file), AnalysisResults.class);
        } else {
            results = new AnalysisResults();
        }

        final File[] listFiles = new File(settings.getCsvDirectory()).listFiles();
        int i = 0;
        for (final File child : listFiles) {
            if (child.isDirectory()) {
                if (!results.getVersionNumbers().contains(child.getName())) {
                    progressMonitor.updateProgres((double) i / (double) listFiles.length, "Processing " + child.getName());
                    processExistingVersionFolder(child.getName());
                }
            }

            i++;
        }
        FileUtils.write(file, gson.toJson(results));

        if (settings.isRunNewDoorsAnalysis() || settings.isGenerateIssueLists() || settings.isUpdateProgressReportExcelSheet()) {
            processNewVersion();
        }

        return results;
    }

    private void processNewVersion() {
        final String version = new SimpleDateFormat("yyyyMMdd").format(new Date());

        final WorkflowModel workflowModel = factory.createWorkflowModel();

        final ModuleSet moduleSet = factory.createModuleSet();
        final ModuleSetEntry moduleSetEntry = factory.createModuleSetEntry();
        moduleSetEntry.setType("csvfolder");
        moduleSetEntry.setReference(new File(settings.getCsvDirectory(), version).getAbsolutePath());
        moduleSet.getModuleSetEntries().add(moduleSetEntry);
        workflowModel.getElements().add(moduleSet);

        final ModelConstructorStep codeBeamerModelConstructorStep = factory.createModelConstructorStep();
        codeBeamerModelConstructorStep.setName("CodeBeamerModelConstructor");
        codeBeamerModelConstructorStep.getFeatures().add(createSourceFeature(moduleSet));
        codeBeamerModelConstructorStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"));
        workflowModel.getElements().add(codeBeamerModelConstructorStep);
        final Target codeBeamerModelConstructorTarget = createTarget(codeBeamerModelConstructorStep);
        workflowModel.getElements().add(codeBeamerModelConstructorTarget);

        if (settings.isRunNewDoorsAnalysis()) {
            final ModelOperationStep doorsExportStep = factory.createModelOperationStep();
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
            final ModelOperationStep updateProgressReportStep = factory.createModelOperationStep();
            updateProgressReportStep.setName("UpdateExcelOp");
            updateProgressReportStep.getFeatures().add(createDependencyFeature("codeBeamerModels", codeBeamerModelConstructorStep));
            updateProgressReportStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.UpdateExcelOp"));
            workflowModel.getElements().add(createTarget(updateProgressReportStep));
            workflowModel.getElements().add(createSimpleVariable("sourceExcelFile", settings.getProgressReportSourceFile()));
            workflowModel.getElements().add(createSimpleVariable("targetExcelFile", settings.getProgressReportTargetFile()));
        }

        if (settings.isGenerateIssueLists()) {
            final ModelOperationStep exportIssueListsStep = factory.createModelOperationStep();
            exportIssueListsStep.setName("ExportIssueListsOp");
            exportIssueListsStep.getFeatures().add(createDependencyFeature("codeBeamerModels", codeBeamerModelConstructorStep));
            exportIssueListsStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.ExportIssueListsOp"));
            workflowModel.getElements().add(createTarget(exportIssueListsStep));
            workflowModel.getElements().add(createSimpleVariable("issueFolder", settings.getIssueListsDirectory()));
        }

        try {
            final Map<Target, List<ModelObject>> workflowResults = new WorkflowProcessor().runWorkflowModel(workflowModel);

            final Version v = createVersion(version, workflowResults.get(codeBeamerModelConstructorTarget));
            results.getVersions().add(v);
        } catch (final WorkflowException e) {
            e.printStackTrace();
        }
    }

    private Target createTarget(final Step step) {
        final Target target;
        target = factory.createTarget();
        target.setStep(step);
        return target;
    }

    private void processExistingVersionFolder(final String versionFolder) {
        final ModuleSet moduleSet = factory.createModuleSet();
        final ModuleSetEntry moduleSetEntry = factory.createModuleSetEntry();
        moduleSetEntry.setType("csvfolder");
        moduleSetEntry.setReference(new File(settings.getCsvDirectory(), versionFolder).getAbsolutePath());
        moduleSet.getModuleSetEntries().add(moduleSetEntry);

        final ModelConstructorStep modelConstructorStep = factory.createModelConstructorStep();
        modelConstructorStep.setName("CodeBeamerModelConstructor");
        modelConstructorStep.getFeatures().add(createSourceFeature(moduleSet));
        modelConstructorStep.getFeatures().add(createImplementationFeature("de.jpwinkler.daf.fap5.CodeBeamerModelConstructor"));

        final WorkflowModel workflowModel = factory.createWorkflowModel();
        workflowModel.getElements().add(moduleSet);
        workflowModel.getElements().add(modelConstructorStep);

        final Target target = factory.createTarget();
        target.setStep(modelConstructorStep);
        workflowModel.getElements().add(target);

        try {
            final Map<Target, List<ModelObject>> workflowResult = new WorkflowProcessor().runWorkflowModel(workflowModel);

            final Version version = createVersion(versionFolder, workflowResult.get(target));

            results.getVersions().add(version);

        } catch (final WorkflowException e) {
            e.printStackTrace();
        }
    }

    private Version createVersion(final String versionString, final List<ModelObject> codeBeamerModels) {
        final Version version = new Version();
        version.setVersionString(versionString);

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

        snapshot.setNumRequirements(cbm.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_COUNT));
        snapshot.setNumTodos(cbm.getIntMetric(CodeBeamerConstants.METRIC_OPEN_TODOS));
        if (snapshot.getNumRequirements() > 0) {
            snapshot.setRequirementsOpen(cbm.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_OPEN));
            snapshot.setRequirementsSpecified(cbm.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_SPECIFIED));
            snapshot.setRequirementsFollowUp(cbm.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP));
            snapshot.setRequirementsFollowUpHashtags(cbm.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_FOLLOW_UP_HASHTAGS));
            snapshot.setRequirementsAgreed(cbm.getDoubleMetric(CodeBeamerConstants.METRIC_MATURITY_AGREED));
        }
        snapshot.setNumErrorMissingObjectType(cbm.getIntMetric(CodeBeamerConstants.METRIC_EMPTY_OBJECT_TYPE));
        snapshot.setNumErrorWrongHeading(cbm.getIntMetric(CodeBeamerConstants.METRIC_HEADING_AS_REQUIREMENT_COUNT));
        snapshot.setNumErrorInformationWithLink(cbm.getIntMetric(CodeBeamerConstants.METRIC_INFORMATION_WITH_LINK));
        snapshot.setNumErrorRequirementWithoutLink(cbm.getIntMetric(CodeBeamerConstants.METRIC_REQUIREMENT_WITHOUT_LINK));
        return snapshot;
    }

    private SourceFeature createSourceFeature(final ModuleSet moduleSet) {
        final SourceFeature sourceFeature = factory.createSourceFeature();
        sourceFeature.setModuleSet(moduleSet);
        return sourceFeature;
    }

    private ImplementationFeature createImplementationFeature(final String implName) {
        final ImplementationFeature implementationFeature = factory.createImplementationFeature();
        implementationFeature.setImplementation(implName);
        return implementationFeature;
    }

    private DependencyFeature createDependencyFeature(final String name, final Step step, final Variable... variables) {
        final DependencyFeature dependencyFeature = factory.createDependencyFeature();
        dependencyFeature.setName(name);
        dependencyFeature.setStep(step);
        for (final Variable v : variables) {
            dependencyFeature.getVariables().add(v);
        }
        return dependencyFeature;
    }

    private Variable createSimpleVariable(final String name, final String value) {
        final SimpleVariable variable = factory.createSimpleVariable();
        variable.setName(name);
        variable.setValue(value);

        return variable;
    }
}
