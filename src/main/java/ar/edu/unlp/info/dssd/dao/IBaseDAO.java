package ar.edu.unlp.info.dssd.dao;


import java.io.Serializable;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public interface IBaseDAO<T>{

	public List<T> listar();
	
	public void guardar(T u);
	
	public void eliminar(Long id);
	
	public T buscar(Long id);
	
	public void modificar(T u);
	
	public void eliminar(Serializable u);
}
