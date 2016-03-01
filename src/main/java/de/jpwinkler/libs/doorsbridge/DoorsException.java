package de.jpwinkler.libs.doorsbridge;

/**
 * A {@link DoorsException} is thrown when a DXL script aborts its normal
 * execution using the 'throw()' function provided in the file
 * 'dxl/pre/exception_handling.dxl'. The exception instance will contain a
 * message indicating the kind of error, which is provided by the dxl script.
 *
 * @author jonwink
 *
 */
public class DoorsException extends Exception {

    private static final long serialVersionUID = 1L;

    public DoorsException() {
        super();
    }

    public DoorsException(final String message) {
        super(message);
    }

}
