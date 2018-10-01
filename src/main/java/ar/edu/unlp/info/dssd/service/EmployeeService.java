package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Employee;
import ar.edu.unlp.info.dssd.model.EmployeeType;
import ar.edu.unlp.info.dssd.model.dto.EmployeeDTO;
import ar.edu.unlp.info.dssd.repository.EmployeeRepository;
import ar.edu.unlp.info.dssd.repository.EmployeeTypeRepository;

@Service
public class EmployeeService implements BasicService<Employee> {
	
	private static final String RESOURCE = "Empleado";
	private static final String TYPE_RESOURCE = "Tipo de Empleado";
	
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
	public Employee getById(String id) throws NoElementFoundException {
		Optional<Employee> employee = this.repository.findById(Long.parseLong(id));
		if (employee.isPresent()) {
			return employee.get();
		}else{
			throw new NoElementFoundException(RESOURCE, id);
		}
	}

	@Override
	@Transactional
	public void deleteById(String id) throws NoElementFoundException {
		if(this.repository.existsById(Long.parseLong(id)))
			this.repository.deleteById(Long.parseLong(id));
		else
			throw new NoElementFoundException(RESOURCE, id);
	}

	@Override
	public Employee create(Employee employee) {
		return this.repository.save(employee);		
	}
	
	@Transactional
	public Employee create(EmployeeDTO employee) throws NoElementFoundException {
		Employee emp = new Employee(employee);
		Optional<EmployeeType> et = this.employeeTypeRepository.findById(employee.getEmployeeType());
		if (et.isPresent()) {
			emp.setEmployeeType(et.get());
			return this.create(emp);
		}else {
			throw new NoElementFoundException(TYPE_RESOURCE, Long.toString(employee.getEmployeeType()));
		}
	}

	@Override
	@Transactional
	public Employee update(String id, Employee employee) throws NoElementFoundException {
		if (this.repository.existsById(Long.parseLong(id))) {
			employee.setId(Long.parseLong(id));
			return this.repository.save(employee);
		}else {
			throw new NoElementFoundException(RESOURCE, id);
		}
	}
	
	@Override
	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
		
	}

}
