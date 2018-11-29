package ar.edu.unlp.info.dssd.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.info.dssd.exceptions.CredentialsException;
import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.dto.LoginDTO;
import ar.edu.unlp.info.dssd.repository.EmployeeRepository;

@Service
public class SessionService {
	
	@Autowired
	private EmployeeRepository repository;

	public Long login(@Valid LoginDTO login) throws CredentialsException {
		Optional<Employee> employee = this.repository.findByEmail(login.getUsername());
		if (employee.isPresent() && this.isValidCredentials(employee.get(), login)) {
			return employee.get().getId();
		}
		throw new CredentialsException();
	}

	private boolean isValidCredentials(Employee employee,  LoginDTO login) {
		return employee.getPassword().equals(login.getPassword());
	}

}
