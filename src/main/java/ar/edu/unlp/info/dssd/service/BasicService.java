package ar.edu.unlp.info.dssd.service;

import java.util.List;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;


public interface BasicService<T> {
	
	List<T> getAll();
	
	T getById(String id) throws NoElementFoundException;
	
	void deleteById(String id) throws NoElementFoundException;
	
	T save(T element) throws NoElementFoundException;
	
	void deleteAll();
	

}
