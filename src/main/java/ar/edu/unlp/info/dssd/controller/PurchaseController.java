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

import ar.edu.unlp.info.dssd.exceptions.MissingArgumentException;
import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.dto.PurchaseDTO;
import ar.edu.unlp.info.dssd.service.PurchaseService;

@Controller
@RequestMapping(value = "/purhcase")
public class PurchaseController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PurchaseController.class);
	
	@Autowired
	private PurchaseService service;
	
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> createProduct(@RequestBody @Valid PurchaseDTO purchase, BindingResult bindingResult) throws NoElementFoundException, MissingArgumentException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		this.service.buyItems(purchase);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity <String> missingArgumentException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
