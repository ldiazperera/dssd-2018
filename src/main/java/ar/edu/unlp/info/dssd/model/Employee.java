package ar.edu.unlp.info.dssd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Employee {
		
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
		@Column(name = "id", nullable = false)
		private Long id;
		
		@Column(name="first_name", nullable = false)
		private String firstname;
		
		@Column(name="surname", nullable = false)
		private String surname;
		
		@Column(name="email", nullable = false)
		private String email;

		@Column(name="password", nullable = false)
		private String password;

		@ManyToOne
		@JoinColumn(name="employee_type_fk")
		private EmployeeType employeeType;

		public Employee() {
			// Default constructor
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getFirstname() {
			return firstname;
		}

		public void setFirstname(String firstname) {
			this.firstname = firstname;
		}

		public String getSurname() {
			return surname;
		}

		public void setSurname(String surname) {
			this.surname = surname;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public EmployeeType getEmployeeType() {
			return employeeType;
		}

		public void setEmployeeType(EmployeeType employeeType) {
			this.employeeType = employeeType;
		}
		
}
