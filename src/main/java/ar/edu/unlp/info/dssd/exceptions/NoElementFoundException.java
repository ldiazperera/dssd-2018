package ar.edu.unlp.info.dssd.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Elemento no encontrado")
public class NoElementFoundException extends Exception {

	public NoElementFoundException() {
		super();
	}

	public NoElementFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NoElementFoundException(String message) {
		super(message);
	}

	public NoElementFoundException(Throwable cause) {
		super(cause);
	}

}
