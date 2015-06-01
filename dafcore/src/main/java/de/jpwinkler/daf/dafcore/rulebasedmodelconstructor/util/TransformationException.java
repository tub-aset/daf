package de.jpwinkler.daf.dafcore.rulebasedmodelconstructor.util;

public class TransformationException extends Exception {

    private static final long serialVersionUID = 1L;

    public TransformationException() {
        super();
    }

    public TransformationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public TransformationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TransformationException(String message) {
        super(message);
    }

    public TransformationException(Throwable cause) {
        super(cause);
    }

}
