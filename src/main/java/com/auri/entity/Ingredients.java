package com.auri.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredients {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private float unitPrice;
	private float mrp;
	private String packedOn;
	private String validitiy;
	private int sku;
	private int currentSKU;
	private int unitValue;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public float getMrp() {
		return mrp;
	}
	public void setMrp(float mrp) {
		this.mrp = mrp;
	}

	public String getPackedOn() {
		return packedOn;
	}
	public void setPackedOn(String packedOn) {
		this.packedOn = packedOn;
	}
	public String getValiditiy() {
		return validitiy;
	}
	public void setValiditiy(String validitiy) {
		this.validitiy = validitiy;
	}
	public int getSku() {
		return sku;
	}
	public void setSku(int sku) {
		this.sku = sku;
	}
	public int getCurrentSKU() {
		return currentSKU;
	}
	public void setCurrentSKU(int currentSKU) {
		this.currentSKU = currentSKU;
	}
	public int getUnitValue() {
		return unitValue;
	}
	public void setUnitValue(int unitValue) {
		this.unitValue = unitValue;
	}
	
	
	
	
}
