package ar.edu.unlp.info.dssd.service;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.dto.LoginDTO;
import ar.edu.unlp.info.dssd.repository.EmployeeRepository;

@Service
public class SessionService {
	
	@Autowired
	private EmployeeRepository repository;

	public boolean login(@Valid LoginDTO login) {
		Optional<Employee> employee = this.repository.findByEmail(login.getUsername());
		if (employee.isPresent()) {
			return employee.get().getPassword().equals(login.getPassword());
		}
		return false;
	}

}
