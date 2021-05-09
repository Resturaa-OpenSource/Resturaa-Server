package com.auri.entity;

import javax.persistence.Embeddable;

@Embeddable
public class VariantValue {

	private String item;
	private String value;
	
	public String getItem() {
		return item;
	}
	
	public void setItem(String item) {
		this.item = item;
	}
	
	public String getValue() {
		return value;
		
	}
	
	public void setValue(String value) {
		this.value = value;
	}
}
