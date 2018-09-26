package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.repository.EmployeeRepository;

@Service
public class EmployeeService implements BasicService<Employee> {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Transactional(readOnly = true)
	public List<Employee> getAll(){
		return this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Employee> getById(String id) {
		return this.repository.findById(Long.parseLong(id));
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		this.repository.deleteById(Long.parseLong(id));
	}

	@Override
	public void create(Employee element) {
		this.repository.save(element);		
	}

	@Override
	public void update(String id, Employee element) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
		
	}

}
