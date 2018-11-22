package ar.edu.unlp.info.dssd.exceptions;

public class CredentialsException extends Exception {
	
	private static final long serialVersionUID = -7611286073335230178L;
	
	private static final String MESSAGE = "Usuario y/o contrasña incorrectos";
	
	public CredentialsException() {
		super(MESSAGE);
	}

}
