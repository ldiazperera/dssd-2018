package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;


public interface BasicService<T> {
	
	List<T> getAll();
	
	Optional<T> getById(String id) throws NoElementFoundException;
	
	void deleteById(String id) throws NoElementFoundException;
	
	T create(T element);
	
	T update(String id, T element) throws NoElementFoundException;
	
	void deleteAll();
	

}
