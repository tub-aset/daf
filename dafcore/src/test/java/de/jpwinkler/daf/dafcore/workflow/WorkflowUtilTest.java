package de.jpwinkler.daf.dafcore.workflow;

import static org.junit.Assert.*;

import org.junit.Test;

import de.jpwinkler.daf.workflowdsl.ForFeature;
import de.jpwinkler.daf.workflowdsl.ImplementationFeature;
import de.jpwinkler.daf.workflowdsl.ModelConstructorStep;
import de.jpwinkler.daf.workflowdsl.ModelOperationStep;
import de.jpwinkler.daf.workflowdsl.Step;

public class WorkflowUtilTest {

    @Test
    public void testGetImplementation() {
        final ModelConstructorStep modelConstructorStep = new ModelConstructorStep();
        final ImplementationFeature implementation = new ImplementationFeature();
        implementation.setImplementation("abc");
        modelConstructorStep.getFeatures().add(implementation);

        assertEquals("abc", WorkflowUtil.getImplementation(modelConstructorStep));

        final ModelOperationStep modelOperationStep = new ModelOperationStep();
        modelOperationStep.getFeatures().add(implementation);

        assertEquals("abc", WorkflowUtil.getImplementation(modelOperationStep));

        final ModelConstructorStep stepWithoutImplementation = new ModelConstructorStep();
        stepWithoutImplementation.getFeatures().add(new ForFeature());

        assertEquals(null, WorkflowUtil.getImplementation(stepWithoutImplementation));
    }

    @Test(expected = RuntimeException.class)
    public void testGetImplementation2() {
        WorkflowUtil.getImplementation(new Step());
    }

}
