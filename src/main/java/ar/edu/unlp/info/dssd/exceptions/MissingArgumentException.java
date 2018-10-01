package ar.edu.unlp.info.dssd.exceptions;

public class MissingArgumentException extends Exception {

	private static final long serialVersionUID = 240462530574074864L;
	
	private static final String MISSING_ARGUMENTS_ERROR = "Unos o más campos obligatorios están vacíos";

	public MissingArgumentException() {
		super(MISSING_ARGUMENTS_ERROR);
	}
	
	

}
