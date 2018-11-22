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
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.exceptions.MissingArgumentException;
import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.dto.EmployeeDTO;
import ar.edu.unlp.info.dssd.service.EmployeeService;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Employee>> getEmployees(@PathVariable("page") Optional<String> page, @PathVariable("size") Optional<String> size, @PathVariable("sort") Optional<String> sort, Pageable pageable) {
		return new ResponseEntity<>(this.service.getAll(pageable), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) throws NoElementFoundException {
		return new ResponseEntity<>(this.service.getById(id),HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employee, BindingResult bindingResult) throws NoElementFoundException, MissingArgumentException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		return new ResponseEntity<>(this.service.create(employee),HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody @Valid EmployeeDTO employee, BindingResult bindingResult) throws NoElementFoundException, MissingArgumentException {
		if (bindingResult.hasErrors()) {
			throw new MissingArgumentException();
		}
		return new ResponseEntity<>(this.service.update(id, employee),HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) throws NoElementFoundException {
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@ExceptionHandler(NoElementFoundException.class)
    public ResponseEntity <String> notFoundException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(MissingArgumentException.class)
    public ResponseEntity <String> missingArgumentException(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
	
}
