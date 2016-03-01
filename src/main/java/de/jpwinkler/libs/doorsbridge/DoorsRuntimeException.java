package de.jpwinkler.libs.doorsbridge;

public class DoorsRuntimeException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DoorsRuntimeException(final String message) {
        super(message);
    }

    public DoorsRuntimeException(final Throwable cause) {
        super(cause);
    }

    public DoorsRuntimeException() {
        super();
    }

}
