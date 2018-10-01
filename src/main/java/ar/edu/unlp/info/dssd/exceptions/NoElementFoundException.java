package ar.edu.unlp.info.dssd.exceptions;

import java.text.MessageFormat;

public class NoElementFoundException extends Exception {
	
	private static final  String MESSAGE = "Elemento no encontrado";
	
	private static final String DETAILED_MESSAGE = "El {0} {1} no fue encontrado";

	private static final long serialVersionUID = 3761851172069238056L;

	public NoElementFoundException() {
		super(MESSAGE);
	}
	
	public NoElementFoundException(String resource, String elem) {
		super(MessageFormat.format(DETAILED_MESSAGE, resource, elem));
	}
	
	public NoElementFoundException(String resource, String elem, Throwable cause) {
		super(MessageFormat.format(DETAILED_MESSAGE, resource, elem), cause);
	}

	public NoElementFoundException(Throwable cause) {
		super(MESSAGE,cause);
	}

}
