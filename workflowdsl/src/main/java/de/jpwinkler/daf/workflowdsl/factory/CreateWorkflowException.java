package de.jpwinkler.daf.workflowdsl.factory;

public class CreateWorkflowException extends Exception {

    private static final long serialVersionUID = 1L;

    public CreateWorkflowException() {
        super();
    }

    public CreateWorkflowException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public CreateWorkflowException(final String message) {
        super(message);
    }

    public CreateWorkflowException(final Throwable cause) {
        super(cause);
    }

}
