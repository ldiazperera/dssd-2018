package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.EmployeeType;
import ar.edu.unlp.info.dssd.model.dto.EmployeeDTO;
import ar.edu.unlp.info.dssd.repository.EmployeeRepository;
import ar.edu.unlp.info.dssd.repository.EmployeeTypeRepository;

@Service
public class EmployeeService implements BasicService<Employee> {
	
	@Autowired
	private EmployeeRepository repository;
	
	@Autowired
	private EmployeeTypeRepository employeeTypeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Employee> getAll(){
		return this.repository.findAll();
	}
	
	@Override
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
	public Employee create(Employee employee) {
		return this.repository.save(employee);		
	}
	
	@Transactional
	public Employee create(EmployeeDTO employee) {
		Employee emp = new Employee(employee);
		Optional<EmployeeType> et = this.employeeTypeRepository.findById(employee.getEmployeeType());
		if (et.isPresent()) {
			emp.setEmployeeType(et.get());
			return this.create(emp);
		}
		return null;
	}

	@Override
	@Transactional
	public Employee update(String id, Employee employee) {
		if (this.repository.existsById(Long.parseLong(id))) {
			employee.setId(Long.parseLong(id));
			return this.repository.save(employee);
		}
		return null;
	}
	
	@Override
	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
		
	}

}
