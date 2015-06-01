package de.jpwinkler.daf.doorsbridge;

public class DoorsException extends Exception {

	private static final long serialVersionUID = 1L;

	public DoorsException() {
		super();
	}

	public DoorsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public DoorsException(String message, Throwable cause) {
		super(message, cause);
	}

	public DoorsException(String message) {
		super(message);
	}

	public DoorsException(Throwable cause) {
		super(cause);
	}
	
}
