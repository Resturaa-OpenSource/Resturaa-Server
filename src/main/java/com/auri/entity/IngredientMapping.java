package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import javax.persistence.Id;

@Entity
public class IngredientMapping {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int productID;
	private int ingrediantID;
	private int productVarientID;
	private String ingrediantName;
	private float value;
	private float totalExpense;
	private String varientName;
	private String productName;
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	public int getProductID() {
		return productID;
	}
	public void setProductID(int productID) {
		this.productID = productID;
	}
	public int getIngrediantID() {
		return ingrediantID;
	}
	public void setIngrediantID(int ingrediantID) {
		this.ingrediantID = ingrediantID;
	}
	public int getProductVarientID() {
		return productVarientID;
	}
	public void setProductVarientID(int productVarientID) {
		this.productVarientID = productVarientID;
	}
	public String getIngrediantName() {
		return ingrediantName;
	}
	public void setIngrediantName(String ingrediantName) {
		this.ingrediantName = ingrediantName;
	}
	public float getValue() {
		return value;
	}
	public void setValue(float value) {
		this.value = value;
	}
	public float getTotalExpense() {
		return totalExpense;
	}
	public void setTotalExpense(float totalExpense) {
		this.totalExpense = totalExpense;
	}
	public String getVarientName() {
		return varientName;
	}
	public void setVarientName(String varientName) {
		this.varientName = varientName;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
}
