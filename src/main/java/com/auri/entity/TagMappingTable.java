package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TagMappingTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tagmappingID;
	private int productID;
	private int tagID;
	
	
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getTagID() {
		return tagID;
	}
	public void setTagID(int tagID) {
		this.tagID = tagID;
	}
	public int getTagmappingID() {
		return tagmappingID;
	}
	public void setTagmappingID(int tagmappingID) {
		this.tagmappingID = tagmappingID;
	}
	
}
