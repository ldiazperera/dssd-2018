package ar.edu.unlp.info.dssd.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import ar.edu.unlp.info.dssd.model.dto.ProductDTO;

@Entity
public class Product {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", nullable = false)
	private Long id;
	
	@Column(name="name", nullable = false)
	private String name;
	
	@Column(name="cost_price", nullable = false)
	private Double costprice;

	@Column(name="sale_price", nullable = false)
	private Double saleprice;
	
	@Column(name="stock", nullable = false)
	private Integer stock;
	
	@ManyToOne
	@JoinColumn(name="product_type_fk")
	private ProductType productType;
	
	public Product(){
		//Default constructor
	}
	
	public Product(ProductDTO product) {
		this.name = product.getName();
		this.costprice = product.getCostprice();
		this.saleprice = product.getSaleprice();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

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

	public ProductType getProductType() {
		return productType;
	}

	public void setProductType(ProductType productType) {
		this.productType = productType;
	}

}
