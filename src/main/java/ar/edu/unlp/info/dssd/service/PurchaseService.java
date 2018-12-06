package ar.edu.unlp.info.dssd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.model.Purchase;
import ar.edu.unlp.info.dssd.model.dto.PurchaseDTO;
import ar.edu.unlp.info.dssd.repository.PurchaseRepository;


@Service
public class PurchaseService {
	
	@Autowired
	private PurchaseRepository repository;
	
	@Autowired
	private ProductService service;
	
	@Transactional
	public void buyItems(PurchaseDTO purchaseDto) throws NoElementFoundException {
		Purchase purchase = new Purchase();
		Product product = this.service.getById(purchaseDto.getItem().toString());
		purchase.setDiscount(purchaseDto.getDiscount());
		purchase.setBuyer(purchaseDto.getUsername());
		purchase.setAmount(purchaseDto.getAmount());
		purchase.setProduct(product);
		this.updateProduct(product);
		this.repository.save(purchase);
	}


	private void updateProduct(Product product) throws NoElementFoundException {
		Integer newStock = product.getStock() - 1;
		product.setStock(newStock);
		this.service.save(product);
	}

}
