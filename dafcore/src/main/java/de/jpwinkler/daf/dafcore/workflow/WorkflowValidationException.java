package de.jpwinkler.daf.dafcore.workflow;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

public class WorkflowValidationException extends Exception {

    private static final long serialVersionUID = 1L;

    private final EObject source;

    public WorkflowValidationException() {
        super();
        source = null;
    }

    public WorkflowValidationException(final String message) {
        super(message);
        source = null;
    }

    public WorkflowValidationException(final EObject source, final String message) {
        super(message);
        this.source = source;
    }

    @Override
    public String getMessage() {
        if (source == null) {
            return super.getMessage();
        } else {
            return String.format("line %d: %s", NodeModelUtils.getNode(source).getStartLine(), super.getMessage());
        }
    }
}
