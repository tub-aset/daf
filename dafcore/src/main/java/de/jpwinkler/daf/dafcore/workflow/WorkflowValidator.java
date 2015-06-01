package de.jpwinkler.daf.dafcore.workflow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import de.jpwinkler.daf.workflowdsl.workflowDsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.workflowDsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.workflowDsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Step;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Target;
import de.jpwinkler.daf.workflowdsl.workflowDsl.Variable;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowElement;
import de.jpwinkler.daf.workflowdsl.workflowDsl.WorkflowModel;

public class WorkflowValidator {

    private final List<String> stepNames = new ArrayList<>();
    private final List<String> variableNames = new ArrayList<>();
    private final List<String> moduleSetNames = new ArrayList<>();

    public void validate(final WorkflowModel workflowModel) throws WorkflowValidationException {

        stepNames.clear();
        variableNames.clear();
        moduleSetNames.clear();

        if (WorkflowUtil.getTargets(workflowModel).size() == 0) {
            throw new WorkflowValidationException(workflowModel, "Workflow has no target.");
        }

        for (final WorkflowElement workflowElement : workflowModel.getElements()) {

            if (workflowElement instanceof ModuleSet) {
                validateModuleSet((ModuleSet) workflowElement);
            } else if (workflowElement instanceof Variable) {
                validateVariable((Variable) workflowElement);
            } else if (workflowElement instanceof ModelConstructorStep) {
                validateModelConstructorStep((ModelConstructorStep) workflowElement);
            } else if (workflowElement instanceof ModelOperationStep) {
                validateModelOperationStep((ModelOperationStep) workflowElement);
            } else if (workflowElement instanceof Target) {
                validateTarget((Target) workflowElement);
            } else {
                throw new UnsupportedOperationException(String.format("Cannot validate workflow element %s.", workflowElement.getClass().getName()));
            }

        }

    }

    private void validateTarget(final Target workflowElement) {
        // do nothing
    }

    private void validateModelOperationStep(final ModelOperationStep modelOperationStep) throws WorkflowValidationException {
        if (stepNames.contains(modelOperationStep.getName())) {
            throw new WorkflowValidationException(modelOperationStep, String.format("A step with name %s is already defined.", modelOperationStep.getName()));
        }

        stepNames.add(modelOperationStep.getName());

        if (modelOperationStep.getFeatures().stream().filter(f -> f instanceof ImplementationFeature).collect(Collectors.counting()) != 1) {
            throw new WorkflowValidationException(modelOperationStep, String.format("Model Operation %s must have exactly one implementation.", modelOperationStep.getName()));
        }

        validateDependencies(modelOperationStep);

        if (checkForCycles(modelOperationStep, new ArrayList<>())) {
            throw new WorkflowValidationException(modelOperationStep, String.format("Model Operation %s has cyclic dependencies.", modelOperationStep.getName()));
        }
    }

    private void validateDependencyFeature(final DependencyFeature dependencyFeature) throws WorkflowValidationException {
        final List<String> dependencyVariableNames = new ArrayList<>();
        for (final Variable variable : dependencyFeature.getVariables()) {
            if (dependencyVariableNames.contains(variable.getName())) {
                throw new WorkflowValidationException(variable, String.format("A variable with name %s is already defined.", variable.getName()));
            }
            dependencyVariableNames.add(variable.getName());
        }

    }

    private boolean checkForCycles(final Step step, final List<Step> stepsSoFar) {

        for (final DependencyFeature dependencyFeature : WorkflowUtil.getDependencies(step.getFeatures())) {
            if (stepsSoFar.contains(dependencyFeature.getStep())) {
                return true;
            }

            final List<Step> newSteps = new ArrayList<>(stepsSoFar);
            newSteps.add(dependencyFeature.getStep());
            if (checkForCycles(dependencyFeature.getStep(), newSteps)) {
                return true;
            }
        }
        return false;
    }

    private void validateModelConstructorStep(final ModelConstructorStep modelConstructorStep) throws WorkflowValidationException {
        if (stepNames.contains(modelConstructorStep.getName())) {
            throw new WorkflowValidationException(modelConstructorStep, String.format("A step with name %s is already defined.", modelConstructorStep.getName()));
        }

        stepNames.add(modelConstructorStep.getName());

        if (modelConstructorStep.getFeatures().stream().filter(f -> f instanceof ImplementationFeature).collect(Collectors.counting()) != 1) {
            throw new WorkflowValidationException(modelConstructorStep, String.format("Model Constructor %s must have exactly one implementation.", modelConstructorStep.getName()));
        }

        if (modelConstructorStep.getFeatures().stream().filter(f -> f instanceof SourceFeature).collect(Collectors.counting()) == 0) {
            throw new WorkflowValidationException(modelConstructorStep, String.format("Model Constructor %s has no source.", modelConstructorStep.getName()));
        }

        validateDependencies(modelConstructorStep);

        if (checkForCycles(modelConstructorStep, new ArrayList<>())) {
            throw new WorkflowValidationException(modelConstructorStep, String.format("Model Constructor %s has cyclic dependencies.", modelConstructorStep.getName()));
        }
    }

    private void validateDependencies(final Step step) throws WorkflowValidationException {
        final List<String> dependencyNames = new ArrayList<>();
        for (final DependencyFeature feature : WorkflowUtil.getDependencies(step.getFeatures())) {
            if (dependencyNames.contains(feature.getName())) {
                throw new WorkflowValidationException(feature, String.format("A dependency with name %s is already defined in step %s.", feature.getName(), step.getName()));
            }
            validateDependencyFeature(feature);
        }
    }

    private void validateVariable(final Variable variable) throws WorkflowValidationException {
        if (variableNames.contains(variable.getName())) {
            throw new WorkflowValidationException(variable, String.format("A global variable with name %s is already defined.", variable.getName()));
        }

        variableNames.add(variable.getName());
    }

    private void validateModuleSet(final ModuleSet moduleSet) throws WorkflowValidationException {
        if (moduleSetNames.contains(moduleSet.getName())) {
            throw new WorkflowValidationException(moduleSet, String.format("A module set with name %s is already defined.", moduleSet.getName()));
        }

        moduleSetNames.add(moduleSet.getName());
    }

}
