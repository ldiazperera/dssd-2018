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
	
	private static final String RESOURCE = "Producto";
	private static final String TYPE_RESOURCE = "Tipo de Producto";
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductTypeRepository productTypeRepository;
	
	@Transactional(readOnly = true)
	public List<Product> getAll(){
		return this.productRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public Product getById(String id) throws NoElementFoundException {
		Optional<Product> product = this.productRepository.findById(Long.parseLong(id));
		if (product.isPresent())
				return product.get();
		else
			throw new NoElementFoundException(RESOURCE, id);
	}

	@Override
	@Transactional
	public void deleteById(String id) throws NoElementFoundException {
		if(this.productRepository.existsById(Long.parseLong(id))) {
			this.productRepository.deleteById(Long.parseLong(id));
		}else {
			throw new NoElementFoundException(RESOURCE, id);
		}
	}

	@Override
	@Transactional
	public Product save(Product product) {
		return this.productRepository.save(product);
	}
	
	@Transactional
	public Product create(ProductDTO product) throws NoElementFoundException {
		Product prod = new Product(product);
		Optional<ProductType> pt = this.productTypeRepository.findById(product.getProductType());
		if (pt.isPresent()) {
			prod.setProductType(pt.get());
			return this.save(prod);
		}else {
			throw new NoElementFoundException(TYPE_RESOURCE, Long.toString(product.getProductType()));
		}
	}

	@Transactional
	public Product update(String id, ProductDTO product) throws NoElementFoundException{
		Product prod = new Product(product);
		if (this.productRepository.existsById(Long.parseLong(id))) {
			prod.setId(Long.parseLong(id));
			Optional<ProductType> pt = this.productTypeRepository.findById(product.getProductType());
			if (pt.isPresent()) {
				prod.setProductType(pt.get());
				return this.save(prod);
			}else {
				throw new NoElementFoundException(TYPE_RESOURCE, Long.toString(product.getProductType()));
			}
		}else{
			throw new NoElementFoundException(RESOURCE, id);
		}
	}

	@Override
	@Transactional
	public void deleteAll() {
		this.productRepository.deleteAll();
		
	}

}
