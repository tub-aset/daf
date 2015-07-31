package de.jpwinkler.daf.workflowdsl;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import de.jpwinkler.daf.workflowdsl.factory.WorkflowFactory;

public class WorkflowFactoryTest {

    @Test
    public void testWorkflowFactory() throws IOException {

        final Workflow workFlow = new WorkflowFactory().createWorkFlow(getClass().getResourceAsStream("test.workflow"));

        assertEquals(workFlow.getElements().size(), 4);
        assertEquals(workFlow.getElements().get(0).getClass(), Target.class);
        assertEquals(workFlow.getElements().get(1).getClass(), ModuleSet.class);
        assertEquals(workFlow.getElements().get(2).getClass(), ModelConstructorStep.class);
        assertEquals(workFlow.getElements().get(3).getClass(), ModelOperationStep.class);

        final ModuleSet moduleSet = (ModuleSet) workFlow.getElements().get(1);

        final ModelConstructorStep constructor = (ModelConstructorStep) workFlow.getElements().get(2);

        final ModelOperationStep op = (ModelOperationStep) workFlow.getElements().get(3);

        assertEquals(op.getName(), "Step2");
        assertEquals(constructor.getFeatures().get(0).getClass(), SourceFeature.class);
        assertEquals(((SourceFeature) constructor.getFeatures().get(0)).getModuleSet(), moduleSet);
    }

}
