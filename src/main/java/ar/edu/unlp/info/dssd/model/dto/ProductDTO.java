package ar.edu.unlp.info.dssd.model.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ProductDTO {
	
	@NotBlank
	private String name;
	
	@NotNull
	private Double costprice;

	@NotNull
	private Double saleprice;
	
	@NotNull
	private Integer stock;
	
	@NotNull
	private long productType;
	
	private String imageUrl;

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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
}
