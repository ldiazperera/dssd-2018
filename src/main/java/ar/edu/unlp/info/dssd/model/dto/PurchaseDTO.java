package ar.edu.unlp.info.dssd.model.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotNull;

public class PurchaseDTO {
	
	@NotNull
	private String username;
	
	@NotNull
	private Long item;
	
	@NotNull
	private BigDecimal discount;
	
	@NotNull
	private BigDecimal amount;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getItem() {
		return item;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public void setItem(Long item) {
		this.item = item;
	}
	
	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	
}
