package ar.edu.unlp.info.dssd.dao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class BaseDao<T> implements IBaseDAO<T>{

	private EntityManager emf;	
	public Class<T> classGeneric;

	
	public EntityManager getEmf() {
		return emf;
	}
	@PersistenceContext
	public void setEmf(EntityManager emf) {
		this.emf = emf;
}

	public Class<T> getClassGeneric() {
		return classGeneric;
	}

	public void setClassGeneric(Class<T> classGeneric) {
		this.classGeneric = classGeneric;
	}

	@Override
	public List<T> listar() {
		EntityManager em= this.emf;
		List<T> retrieve =  (List<T>)(em.createQuery("from " +  classGeneric.getCanonicalName() +  " b")).getResultList();
		em.close();
		return retrieve;
	}

	@Override
	public void guardar(T u) {
		EntityManager em= this.emf;
		em.persist(u);
		em.close();
	}

	@Override
	public void eliminar(Long id) {
		EntityManager em= this.emf;
		
		T u = buscar(id);		
		
		em.remove(em.contains(u) ? u :em.merge(u));
		
		em.close();
		
	}
	
	public void eliminar(Serializable u) {
		EntityManager em= this.emf;
				
		em.remove(em.contains(u) ? u :em.merge(u));
		
		em.close();
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public T buscar(Long id) {
		EntityManager em= this.emf;	
		T retrieve = em.find(classGeneric, id);
		em.close();
		return retrieve;
	}

	@Override
	public void modificar(T u) {
		EntityManager em= this.emf;
		em.merge(u);
		em.close();
	}


	

}
