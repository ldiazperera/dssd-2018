package ar.edu.unlp.info.dssd.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.exceptions.CredentialsException;
import ar.edu.unlp.info.dssd.exceptions.MissingArgumentException;
import ar.edu.unlp.info.dssd.model.dto.LoginDTO;
import ar.edu.unlp.info.dssd.service.SessionService;

@Controller
@RequestMapping(value = "/sessions")
public class SessionController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SessionController.class);
	
	@Autowired
	private SessionService service;
	
	@RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResponseEntity<Long> login(@RequestBody @Valid LoginDTO login, BindingResult bindingResult) throws MissingArgumentException, CredentialsException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		return new ResponseEntity<>(this.service.login(login), HttpStatus.OK);
	}
	
	@ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity <String> missingArgumentException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
	@ExceptionHandler(CredentialsException.class)
    public ResponseEntity <String> badCreedentialsException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

}
