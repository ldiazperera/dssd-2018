package ar.edu.unlp.info.dssd.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
	
	@NotBlank
	private String name;
	
	@NotBlank
	private Double costprice;

	@NotBlank
	private Double saleprice;
	
	@NotBlank
	private Integer stock;
	
	@NotNull
	private long productType;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getCostprice() {
		return costprice;
	}

	public void setCostprice(Double costprice) {
		this.costprice = costprice;
	}

	public Double getSaleprice() {
		return saleprice;
	}

	public void setSaleprice(Double saleprice) {
		this.saleprice = saleprice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public long getProductType() {
		return productType;
	}

	public void setProductType(long productType) {
		this.productType = productType;
	}
	
}
