package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;


@Entity
@Table(name = "product_table")
public class ProductTable {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int itemCode;
	private String itemcodePrefix;
	private String productName;
	private String productNature;
	
	@Lob
	private String productDesc;
	private String productImage;
	private float productPrice;
	private float productTax;
	private float prductRating;
	private int deliveryTime;
	
	
	
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	public String getItemcodePrefix() {
		return itemcodePrefix;
	}
	public void setItemcodePrefix(String itemcodePrefix) {
		this.itemcodePrefix = itemcodePrefix;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductNature() {
		return productNature;
	}
	public void setProductNature(String productNature) {
		this.productNature = productNature;
	}
	public String getProductDesc() {
		return productDesc;
	}
	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	public String getProductImage() {
		return productImage;
	}
	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}
	public float getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}
	public float getProductTax() {
		return productTax;
	}
	public void setProductTax(float productTax) {
		this.productTax = productTax;
	}
	public float getPrductRating() {
		return prductRating;
	}
	public void setPrductRating(float prductRating) {
		this.prductRating = prductRating;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	}
