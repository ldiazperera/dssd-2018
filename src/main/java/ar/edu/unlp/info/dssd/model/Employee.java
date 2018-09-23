package ar.edu.unlp.info.dssd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Employee {

	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="firstname", nullable = false)
	private String firstname;
	
	@Column(name="surname", nullable = false)
	private String surname;
	
	@Column(name="email", nullable = false)
	private String email;

	@Column(name="password", nullable = false)
	private String password;

	@Column(name="employeeType", nullable = false)
	@ManyToOne
	private EmployeeType employeeType;
}
