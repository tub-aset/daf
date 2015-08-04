package de.jpwinkler.daf.workflowdsl.factory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;

import de.jpwinkler.daf.workflowdsl.Workflow;
import de.jpwinkler.daf.workflowdsl.WorkflowLexer;
import de.jpwinkler.daf.workflowdsl.WorkflowParser;

public class WorkflowFactory {


    public Workflow createWorkflow(final InputStream is) throws IOException, CreateWorkflowException {
        final WorkflowLexer lexer = new WorkflowLexer(new ANTLRInputStream(new InputStreamReader(is, Charset.forName("UTF-8"))));
        final WorkflowParser parser = new WorkflowParser(new CommonTokenStream(lexer));

        final WorkflowFactoryListener workflowFactoryListener = new WorkflowFactoryListener();
        parser.addParseListener(workflowFactoryListener);

        parser.workflow();

        final Workflow workflow = workflowFactoryListener.getWorkflow();

        try {
            new ProxyResolver().resolveProxies(workflow);
        } catch (final ProxyResolveException e) {
            throw new CreateWorkflowException(e);
        }

        return workflow;
    }



}
