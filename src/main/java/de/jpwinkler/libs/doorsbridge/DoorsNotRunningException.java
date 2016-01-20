package de.jpwinkler.libs.doorsbridge;

public class DoorsNotRunningException extends DoorsException {

	private static final long serialVersionUID = 1L;
	
	public DoorsNotRunningException() {
		super("Doors is not running.");
	}

}
