package ar.edu.unlp.info.dssd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.service.ProductService;

@Controller
@RequestMapping(value = "/products")
public class ProductController {

	@Autowired
	private ProductService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> getEmployees() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Product> getProductById(@PathVariable String id) {
		return this.service.getById(id)
				.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Product> createProduct(@ModelAttribute Product element) {
		if ( element.getName().equals("") || element.getProductType() == null || 
				element.getSaleprice() == null || element.getCostprice() == null )
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		else{
			this.service.create(element); 
			if (element.getId() != null)
				return new ResponseEntity<>(HttpStatus.OK);
			else
				return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Product> deleteProductById(@PathVariable String id) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		this.service.deleteById(id);
		return this.service.getById(id)
				.map(product -> new ResponseEntity<>(product, HttpStatus.NOT_MODIFIED))
				.orElse(new ResponseEntity<>(HttpStatus.OK));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Product> updateProduct(@PathVariable String id, @ModelAttribute Product element) {
		if (id == null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		this.service.update(id, element);
		return this.service.getById(id)
				.map(product -> new ResponseEntity<>(product, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_MODIFIED));
	}



}
