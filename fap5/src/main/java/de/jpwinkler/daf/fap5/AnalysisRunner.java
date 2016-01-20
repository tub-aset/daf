package de.jpwinkler.daf.fap5;

import de.jpwinkler.daf.dafcore.workflow.WorkflowException;
import de.jpwinkler.daf.dafcore.workflow.WorkflowProcessor;

public class AnalysisRunner {

    public static void main(final String[] args) throws WorkflowException {

        final WorkflowProcessor workflowProcessor = new WorkflowProcessor();

        workflowProcessor.runWorkflow(AnalysisRunner.class.getClassLoader().getResourceAsStream("default.workflow"));


    }

}
