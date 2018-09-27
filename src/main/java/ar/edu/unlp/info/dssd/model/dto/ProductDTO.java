package ar.edu.unlp.info.dssd.model.dto;

public class ProductDTO {
	
	private String name;
	
	private Double costprice;

	private Double saleprice;
	
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

	public long getProductType() {
		return productType;
	}

	public void setProductType(long productType) {
		this.productType = productType;
	}
	
}
