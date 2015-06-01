package de.jpwinkler.daf.doorsbridge.internal;

public class ScriptNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ScriptNotFoundException() {
		super();
	}

	public ScriptNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public ScriptNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public ScriptNotFoundException(String message) {
		super(message);
	}

	public ScriptNotFoundException(Throwable cause) {
		super(cause);
	}

}
