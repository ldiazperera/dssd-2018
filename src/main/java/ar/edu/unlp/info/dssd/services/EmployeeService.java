package ar.edu.unlp.info.dssd.services;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import ar.edu.unlp.info.dssd.dao.EmployeeDAO;
import ar.edu.unlp.info.dssd.dao.FactoryDAO;
import ar.edu.unlp.info.dssd.dao.IEmployeeDAO;
import ar.edu.unlp.info.dssd.model.Employee;

@RestController
@EnableWebMvc
public class EmployeeService {

	public ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("app-ctx.xml");
	private IEmployeeDAO employeeDAO;
	
	public EmployeeService() {
		this.employeeDAO = (IEmployeeDAO) ctx.getBean("employeeDAO");
		
	}
	
	@RequestMapping(value = "/employees/", method = RequestMethod.GET)
	public ResponseEntity<List<Employee>> getEmployees() {
		List<Employee> employees = this.employeeDAO.listar();
		if(employees.isEmpty()){
			return new ResponseEntity<List<Employee>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Employee>>(employees, HttpStatus.OK);
	} 
}
