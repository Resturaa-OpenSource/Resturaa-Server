package com.auri.entity;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;


@Entity
public class ProductVariants {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private int productVariantID;
	
	private int productID;
	private String name;
	private float price;
	
	// list of variables;
	
	@ElementCollection
	@CollectionTable(name = "variant_value", joinColumns = @JoinColumn(name = "product_variantid"))
	private java.util.List<VariantValue> variantValue;

	
	public java.util.List<VariantValue> getVariantValue() {
		return variantValue;
	}
	public void setVariantValue(java.util.List<VariantValue> variantValue) {
		this.variantValue = variantValue;
	}
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getProductVariantID() {
		return productVariantID;
	}
	public void setProductVariantID(int productVariantID) {
		this.productVariantID = productVariantID;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
}
