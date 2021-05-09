package com.auri.entity;

import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Porderentity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String orederName;
	private Date date;
	private float totalAmount;
	private String status;
	
	@ElementCollection
	@CollectionTable(name = "PorederItemList", joinColumns = @JoinColumn(name = "id"))
	private java.util.List<PorederItemList> items;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOrederName() {
		return orederName;
	}

	public void setOrederName(String orederName) {
		this.orederName = orederName;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public float getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(float totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public java.util.List<PorederItemList> getItems() {
		return items;
	}

	public void setItems(java.util.List<PorederItemList> items) {
		this.items = items;
	}
	

}
