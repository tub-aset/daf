package de.jpwinkler.daf.workflowdsl.factory;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.ModuleSet;
import de.jpwinkler.daf.workflowdsl.SimpleVariable;
import de.jpwinkler.daf.workflowdsl.SourceFeature;
import de.jpwinkler.daf.workflowdsl.Target;
import de.jpwinkler.daf.workflowdsl.Variable;
import de.jpwinkler.daf.workflowdsl.Workflow;

public class ProxyResolverTest {

    @Test
    public void testResolveStep() throws ProxyResolveException {
        final Workflow workflow = new Workflow();
        final Target target = new Target();
        final StepProxy proxy = new StepProxy("ref");
        final ModelOperationStep step = new ModelOperationStep();
        final Variable variable = new SimpleVariable();

        workflow.getElements().add(target);
        workflow.getElements().add(variable);
        workflow.getElements().add(step);
        target.setStep(proxy);
        step.setName("ref");

        new ProxyResolver().resolveProxies(workflow);

        assertEquals(step, target.getStep());

        new ProxyResolver().resolveProxies(workflow);

        // Test that already resolved steps wont get touched.
        assertEquals(step, target.getStep());
    }

    @Test(expected = ProxyResolveException.class)
    public void testUnresolvableStep() throws ProxyResolveException {
        final Workflow workflow = new Workflow();
        final Target target = new Target();
        final StepProxy proxy = new StepProxy("ref");

        workflow.getElements().add(target);
        target.setStep(proxy);

        new ProxyResolver().resolveProxies(workflow);
    }

    @Test
    public void testResolveModuleSet() throws ProxyResolveException {
        final Workflow workflow = new Workflow();
        final ModuleSet moduleSet = new ModuleSet();
        moduleSet.setName("ref");
        final ModelConstructorStep step = new ModelConstructorStep();

        final SourceFeature sourceFeature = new SourceFeature();
        sourceFeature.setModuleSet(new ModuleSetProxy("ref"));
        step.getFeatures().add(sourceFeature);
        workflow.getElements().add(moduleSet);
        workflow.getElements().add(step);

        new ProxyResolver().resolveProxies(workflow);

        assertEquals(moduleSet, sourceFeature.getModuleSet());

        new ProxyResolver().resolveProxies(workflow);

        assertEquals(moduleSet, sourceFeature.getModuleSet());

    }

    @Test(expected = ProxyResolveException.class)
    public void testUnresolvableModuleSet() throws ProxyResolveException {
        final Workflow workflow = new Workflow();
        final ModelConstructorStep step = new ModelConstructorStep();
        final SourceFeature sourceFeature = new SourceFeature();
        sourceFeature.setModuleSet(new ModuleSetProxy("ref"));
        step.getFeatures().add(sourceFeature);
        workflow.getElements().add(step);

        new ProxyResolver().resolveProxies(workflow);
    }

}
