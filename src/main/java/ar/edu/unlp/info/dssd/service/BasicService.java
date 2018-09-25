package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;


public interface BasicService<T> {
	
	List<T> getAll();
	
	Optional<T> getById(String id);
	
	void deleteById(String id);
	
	void create(T element);
	
	void update(String id, T element);
	
	void deleteAll();
	

}
