package de.jpwinkler.daf.dafcore;

import java.io.File;

import de.jpwinkler.daf.dafcore.workflow.WorkflowException;
import de.jpwinkler.daf.dafcore.workflow.WorkflowProcessor;

/**
 * Hello world!
 *
 */
public class App
{
    public static void main( final String[] args )
    {
        try {
            new WorkflowProcessor().runWorkFlow(new File(args[0]));
        } catch (final WorkflowException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
