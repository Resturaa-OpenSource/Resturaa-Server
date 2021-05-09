package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_product_table ")
public class StoreProductTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productEnableID;//combination of proId and StoreID
	private int productID;
	private int storeID;
	private boolean enable;
	private int offeID;
	
	
	public int getProductEnableID() {
		return productEnableID;
	}
	public void setProductEnableID(int productEnableID) {
		this.productEnableID = productEnableID;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getStoreID() {
		return storeID;
	}
	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public int getOffeID() {
		return offeID;
	}
	public void setOffeID(int offeID) {
		this.offeID = offeID;
	}
	
	
	
}
