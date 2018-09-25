package ar.edu.unlp.info.dssd.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.service.EmployeeService;

@Controller
@RequestMapping(value = "/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() {
		return new ResponseEntity<>(this.service.getAll(), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Employee> getAllEmployees(@PathVariable String id) {
		return this.service.getById(id)
				.map(employee -> new ResponseEntity<>(employee, HttpStatus.OK))
				.orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
	}
	
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteEmployee(@PathVariable String id) {
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public HttpStatus addEmployee(@RequestBody Employee employee) {
		this.service.create(employee);
		List<Employee> employeeList = this.service.getAll();
		if (employeeList.contains(employee)) {
			return HttpStatus.CREATED;
		} else {
			return HttpStatus.BAD_REQUEST;
		}
	}

}
