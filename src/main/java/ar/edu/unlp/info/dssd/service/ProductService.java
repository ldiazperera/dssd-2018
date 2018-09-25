package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.repository.ProductRepository;

@Service
public class ProductService implements BasicService<Product>{
	
	@Autowired
	private ProductRepository repository;
	
	@Transactional(readOnly = true)
	public List<Product> getAll(){
		return this.repository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Product> getById(String id) {
		return this.repository.findById(Long.parseLong(id));
	}

	@Override
	@Transactional
	public void deleteById(String id) {
		this.repository.deleteById(Long.parseLong(id));
	}

	@Override
	public void create(Product element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(String id, Product element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.repository.deleteAll();
		
	}

}
