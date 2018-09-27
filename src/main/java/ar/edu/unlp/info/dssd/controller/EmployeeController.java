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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.dto.EmployeeDTO;
import ar.edu.unlp.info.dssd.service.EmployeeService;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(EmployeeController.class);

	private static final String ARGUMENTS_MISSING_ERROR = "Unos o más campos obligatorios están vacíos";

	@Autowired
	private EmployeeService service;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getEmployeeById(@PathVariable String id) {
		return this.service.getById(id)
				.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Employee> addEmployee(@RequestBody @Valid EmployeeDTO employee, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			LOGGER.error(ARGUMENTS_MISSING_ERROR);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		this.service.create(employee);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Employee> updateEmployee(@PathVariable String id, @RequestBody Employee employee) {
		this.service.update(id, employee);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
		this.service.deleteById(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
}
