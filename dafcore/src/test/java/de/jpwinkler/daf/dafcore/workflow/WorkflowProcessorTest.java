package de.jpwinkler.daf.dafcore.workflow;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

import de.jpwinkler.daf.dafcore.model.common.ModelObject;
import de.jpwinkler.daf.dafcore.model.csv.CSVFactory;
import de.jpwinkler.daf.dafcore.model.csv.DoorsModule;
import de.jpwinkler.daf.dafcore.model.csv.DoorsObject;
import de.jpwinkler.daf.workflowdsl.factory.CreateWorkflowException;
import de.jpwinkler.daf.workflowdsl.factory.WorkflowFactory;

public class WorkflowProcessorTest {

    public static class Constructor1 extends AbstractStepImpl implements ModelConstructorImpl {

        @Override
        public void setSource(final DoorsModule module) {
        }

        @Override
        public ModelObject execute() {
            final DoorsObject doorsObject = CSVFactory.eINSTANCE.createDoorsObject();
            if (getStringVariable("p") != null) {
                doorsObject.setObjectText(getStringVariable("p"));
            } else {
                doorsObject.setObjectText("");
            }
            return doorsObject;
        }

    }
    public static class Operation1 extends AbstractStepImpl implements ModelOperationImpl {

        @Override
        public ModelObject execute() {
            return getSingleParameter("c1");
        }

    }

    public static class Operation2 extends AbstractStepImpl implements ModelOperationImpl {

        @Override
        public ModelObject execute() {
            System.out.println(getParameterNames());
            final List<ModelObject> c1 = getParameter("c1");
            if (c1 != null) {
                final String string = c1.stream().map(o -> ((DoorsObject) o).getObjectText()).reduce((s1, s2) -> s1 + s2).toString();
                System.out.println(string);
            }
            return null;
        }

    }

    @Test
    public void testRunWorkflow() throws WorkflowException, IOException, CreateWorkflowException {

        new WorkflowProcessor().runWorkflowModel(new WorkflowFactory().createWorkflow(getClass().getResourceAsStream("testRunWorkflow.workflow")));
    }

}
