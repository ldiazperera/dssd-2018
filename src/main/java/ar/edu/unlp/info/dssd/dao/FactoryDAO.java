package ar.edu.unlp.info.dssd.dao;

import org.springframework.stereotype.Repository;

@Repository
public class FactoryDAO {

	private static final FactoryDAO INSTANCE = new FactoryDAO();
	
	private FactoryDAO(){}
	
	public static FactoryDAO getFactoryDAO(){
		return INSTANCE;
	}
	
	public EmployeeDAO getEmployeeDAO(){
		return new EmployeeDAO();
	}
}