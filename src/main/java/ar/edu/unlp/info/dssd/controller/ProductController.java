package ar.edu.unlp.info.dssd.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.model.dto.ProductDTO;
import ar.edu.unlp.info.dssd.service.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	private static final String ARGUMENTS_MISSING_ERROR = "Unos o más campos obligatorios están vacíos";

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getProducts() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable String id) throws NoElementFoundException {
		return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO product, BindingResult bindingResult) throws NoElementFoundException {
		if (bindingResult.hasErrors()) {
			LOGGER.error(ARGUMENTS_MISSING_ERROR);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(this.service.create(product), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) throws NoElementFoundException {
		this.service.update(id, product);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProductById(@PathVariable String id) throws NoElementFoundException {
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
    @ExceptionHandler(NoElementFoundException.class)
    public ResponseEntity <String> exception(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
}
