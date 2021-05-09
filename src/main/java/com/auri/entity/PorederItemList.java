package com.auri.entity;

import javax.persistence.Embeddable;

@Embeddable
public class PorederItemList {

	private int code;
	private String name;
	private float unitPrice;
	private int storeQty;
	private int requiredQty;
	private int reciQty;
	private int sku;
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}
	public int getStoreQty() {
		return storeQty;
	}
	public void setStoreQty(int storeQty) {
		this.storeQty = storeQty;
	}
	public int getRequiredQty() {
		return requiredQty;
	}
	public void setRequiredQty(int requiredQty) {
		this.requiredQty = requiredQty;
	}
	public int getReciQty() {
		return reciQty;
	}
	public void setReciQty(int reciQty) {
		this.reciQty = reciQty;
	}
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	
	
	
}
