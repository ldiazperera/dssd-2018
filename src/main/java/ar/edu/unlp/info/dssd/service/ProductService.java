package ar.edu.unlp.info.dssd.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.model.ProductType;
import ar.edu.unlp.info.dssd.model.dto.ProductDTO;
import ar.edu.unlp.info.dssd.repository.ProductRepository;
import ar.edu.unlp.info.dssd.repository.ProductTypeRepository;

@Service
public class ProductService implements BasicService<Product>{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Transactional(readOnly = true)
	public List<Product> getAll(){
		return this.productRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Optional<Product> getById(String id) throws NoElementFoundException {
		Optional<Product> product = this.productRepository.findById(Long.parseLong(id));
		if (product.isPresent())
				return product ;
		else
			throw new NoElementFoundException("Elemento no encontrado");
	}

	@Override
	@Transactional
	public void deleteById(String id) throws NoElementFoundException {
		if(this.productRepository.findById(Long.parseLong(id)).isPresent())
			this.productRepository.deleteById(Long.parseLong(id));
		else
			throw new NoElementFoundException("Elemento no encontrado");
	}

	@Override
	@Transactional
	public Product create(Product product) {
		return this.productRepository.save(product);
	}
	
	@Transactional
	public Product create(ProductDTO product) {
		Product prod = new Product(product);
		Optional<ProductType> pt = this.productTypeRepository.findById(product.getProductType());
		if (pt.isPresent()) {
			prod.setProductType(pt.get());
			return this.create(prod);
		}
		return null;
	}

	@Override
	@Transactional
	public Product update(String id, Product product) throws NoElementFoundException {
		if (this.productRepository.existsById(Long.parseLong(id))) {
			product.setId(Long.parseLong(id));
			return this.productRepository.save(product);
		}else{
			throw new NoElementFoundException("Elemento no encontrado");
		}
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.productRepository.deleteAll();
		
	}

}
