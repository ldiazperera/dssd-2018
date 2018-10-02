package ar.edu.unlp.info.dssd.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;


public interface BasicService<T> {
	
	Page<T> getAll(Pageable pageable);
	
	T getById(String id) throws NoElementFoundException;
	
	void deleteById(String id) throws NoElementFoundException;
	
	T save(T element) throws NoElementFoundException;
	
	void deleteAll();
	

}
