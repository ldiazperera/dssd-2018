package ar.edu.unlp.info.dssd.model.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

public class PurchaseDTO {
	
	@NotNull
	private String username;
	
	@NotNull
	private List<ItemDTO> items;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<ItemDTO> getItems() {
		return items;
	}

	public void setItems(List<ItemDTO> items) {
		this.items = items;
	}
	
	
	
	

}
