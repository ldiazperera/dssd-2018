package ar.edu.unlp.info.dssd.model.dto;

import javax.validation.constraints.NotBlank;

public class EmployeeDTO {
	
	@NotBlank
	private String firstname;
	
	@NotBlank
	private String surname;
	
	@NotBlank
	private String email;

	@NotBlank
	private String password;

	@NotBlank
	private long employeeType;

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

	public long getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(long employeeType) {
		this.employeeType = employeeType;
	}
	
}
