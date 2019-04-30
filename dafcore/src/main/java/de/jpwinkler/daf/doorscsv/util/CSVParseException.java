package de.jpwinkler.daf.doorscsv.util;

import java.io.IOException;

public class CSVParseException extends IOException {

    private static final long serialVersionUID = 1L;

    public CSVParseException() {
        super();
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
