package ar.edu.unlp.info.dssd.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.exceptions.MissingArgumentException;
import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.model.dto.ProductDTO;
import ar.edu.unlp.info.dssd.service.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService service;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Product>> getProducts(@PathVariable("page") Optional<String> page, @PathVariable("size") Optional<String> size, @PathVariable("sort") Optional<String> sort, Pageable pageable) {
		return new ResponseEntity<>(this.service.getAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable String id) throws NoElementFoundException {
		return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductDTO product, BindingResult bindingResult) throws NoElementFoundException, MissingArgumentException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		return new ResponseEntity<>(this.service.create(product), HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody @Valid ProductDTO product, BindingResult bindingResult) throws NoElementFoundException, MissingArgumentException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		return new ResponseEntity<>(this.service.update(id, product), HttpStatus.OK);
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
    
	@ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity <String> missingArgumentException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
