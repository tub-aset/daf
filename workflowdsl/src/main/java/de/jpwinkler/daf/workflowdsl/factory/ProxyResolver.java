package de.jpwinkler.daf.workflowdsl.factory;

import de.jpwinkler.daf.workflowdsl.DependencyFeature;
import de.jpwinkler.daf.workflowdsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.OperationFeature;
import de.jpwinkler.daf.workflowdsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.Step;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Workflow;
import de.jpwinkler.daf.workflowdsl.WorkflowElement;

public class ProxyResolver {

    private Workflow workflow;

    public void resolveProxies(final Workflow workflow) throws ProxyResolveException {

        this.workflow = workflow;

        for (final WorkflowElement element : workflow.getElements()) {
            if (element instanceof Target) {
                final Target target = (Target) element;

                target.setStep(resolveStep(target.getStep()));
            } else if (element instanceof Step) {
                resolveProxies((Step) element);
            }
        }

    }

    private void resolveProxies(final Step step) throws ProxyResolveException {
        for (final OperationFeature feature : step.getFeatures()) {
            resolveProxies(feature);
        }
    }

    private void resolveProxies(final OperationFeature feature) throws ProxyResolveException {
        if (feature instanceof SourceFeature) {
            final SourceFeature sourceFeature = (SourceFeature) feature;
            sourceFeature.setModuleSet(resolveModuleSet(sourceFeature.getModuleSet()));
        } else if (feature instanceof ForFeature) {
            final ForFeature forFeature = (ForFeature) feature;
            for (final OperationFeature f : forFeature.getFeatures()) {
                resolveProxies(f);
            }
        } else if (feature instanceof DependencyFeature) {
            final DependencyFeature dependencyFeature = (DependencyFeature) feature;
            dependencyFeature.setStep(resolveStep(dependencyFeature.getStep()));
        }
    }

    private Step resolveStep(final Step step) throws ProxyResolveException {
        if (step instanceof StepProxy) {
            final String name = ((StepProxy) step).getStepRef();

            for (final WorkflowElement element : workflow.getElements()) {
                if (element instanceof Step && ((Step) element).getName().equals(name)) {
                    return (Step) element;
                }
            }
            throw new ProxyResolveException(String.format("Could not resolve step %s", name));
        } else {
            return step;
        }
    }

    private ModuleSet resolveModuleSet(final ModuleSet moduleSet) throws ProxyResolveException {
        if (moduleSet instanceof ModuleSetProxy) {
            final String name = ((ModuleSetProxy) moduleSet).getModuleSetRef();
            for (final WorkflowElement element : workflow.getElements()) {
                if (element instanceof ModuleSet && ((ModuleSet) element).getName().equals(name)) {
                    return (ModuleSet) element;
                }
            }
            throw new ProxyResolveException(String.format("Could not resolve module set %s", name));
        } else {
            return moduleSet;
        }
    }
}
