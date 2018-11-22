package ar.edu.unlp.info.dssd.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unlp.info.dssd.exceptions.NoElementFoundException;
import ar.edu.unlp.info.dssd.model.Product;
import ar.edu.unlp.info.dssd.model.Purchase;
import ar.edu.unlp.info.dssd.model.dto.ItemDTO;
import ar.edu.unlp.info.dssd.model.dto.PurchaseDTO;
import ar.edu.unlp.info.dssd.repository.PurchaseRepository;


@Service
public class PurchaseService {
	
	private PurchaseRepository repository;
	
	@Autowired
	private ProductService service;
	
	@Transactional
	public void buyItems(PurchaseDTO purchaseDto) throws NoElementFoundException {
		List<ItemDTO> items = purchaseDto.getItems();
		Purchase purchase = new Purchase();
		purchase.setBuyer(purchaseDto.getUsername());
		purchase.setItems(items.size());
		for(ItemDTO item : items) {
			this.updateProduct(item);
		}
		this.repository.save(purchase);
	}


	private void updateProduct(ItemDTO item) throws NoElementFoundException {
		Product product = this.service.getById(item.getId());
		Integer newStock = product.getStock() - item.getAmount();
		product.setStock(newStock);
		this.service.save(product);
	}

}
