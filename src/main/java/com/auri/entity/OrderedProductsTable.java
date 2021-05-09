package com.auri.entity;

import javax.persistence.Embeddable;

@Embeddable
public class OrderedProductsTable {
	
	private int itemCode;
	private int quantity;
	private float price;
	private float offerValue;
	private boolean isParcel;
	private int productVariantID;
	private String productVarientName;
	private String status;
	private String itemcodePrefix;
	private String productName;
	
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public boolean isParcel() {
		return isParcel;
	}
	public void setParcel(boolean isParcel) {
		this.isParcel = isParcel;
	}
	public int getProductVariantID() {
		return productVariantID;
	}
	public void setProductVariantID(int productVariantID) {
		this.productVariantID = productVariantID;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
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
	public float getOfferValue() {
		return offerValue;
	}
	public void setOfferValue(float offerValue) {
		this.offerValue = offerValue;
	}
	public String getProductVarientName() {
		return productVarientName;
	}
	public void setProductVarientName(String productVarientName) {
		this.productVarientName = productVarientName;
	}
	public int getItemCode() {
		return itemCode;
	}
	public void setItemCode(int itemCode) {
		this.itemCode = itemCode;
	}
	

}
