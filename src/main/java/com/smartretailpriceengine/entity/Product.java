package com.smartretailpriceengine.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "product")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@NotNull(message = "Product name should not be null")
	@NotEmpty(message = "Product name should not be empty")
	@Column(length = 500)
	private String name;

	@Min(value = 0, message = "Base price should not be a negative value")
	@Column(precision = 10, scale = 2)
	private BigDecimal basePrice;

	@NotNull(message = "Category should not be null")
	@NotEmpty(message = "Category should not be empty")
	@Column(length = 500)
	private String category;

	@NotNull(message = "Brand should not be null")
	@NotEmpty(message = "Brand should not be empty")
	@Column(length = 1000)
	private String brand;

	@Min(value = 0, message = "Stock quantity should not be a negative value")
	private int stockQuantity;

	private long createdOn;
	private long modifiedOn;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BigDecimal basePrice) {
		this.basePrice = basePrice;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public int getStockQuantity() {
		return stockQuantity;
	}

	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}

	public long getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(long createdOn) {
		this.createdOn = createdOn;
	}

	public long getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(long modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

}
