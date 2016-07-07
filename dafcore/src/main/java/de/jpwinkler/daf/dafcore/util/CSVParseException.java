package de.jpwinkler.daf.dafcore.util;

public class CSVParseException extends Exception {

    private static final long serialVersionUID = 1L;

    public CSVParseException() {
        super();
    }

    public CSVParseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public CSVParseException(String message, Throwable cause) {
        super(message, cause);
    }

    public CSVParseException(String message) {
        super(message);
    }

    public CSVParseException(Throwable cause) {
        super(cause);
    }

}
