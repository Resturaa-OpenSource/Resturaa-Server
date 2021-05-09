package com.auri.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name= "store_table")
@SequenceGenerator(name="seq", initialValue=1000)

public class StoreTable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private int storeId;
	private String storeName;
	private String email;
	private String password;
	private Date createdDate;
	private String subscription;
	private boolean status;
	private String storeAddress1;
	private String storeAddress2;
	private String state;
	private String country;
	private String storePincode;
	private String storeManager;
	private String storeTaxinfo;
	private String storeType;
	private int storeCounter;
	private String imageLink;
	
	
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public String getSubscription() {
		return subscription;
	}
	public void setSubscription(String subscription) {
		this.subscription = subscription;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public String getStoreAddress1() {
		return storeAddress1;
	}
	public void setStoreAddress1(String storeAddress1) {
		this.storeAddress1 = storeAddress1;
	}
	public String getStoreAddress2() {
		return storeAddress2;
	}
	public void setStoreAddress2(String storeAddress2) {
		this.storeAddress2 = storeAddress2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getStorePincode() {
		return storePincode;
	}
	public void setStorePincode(String storePincode) {
		this.storePincode = storePincode;
	}
	public String getStoreManager() {
		return storeManager;
	}
	public void setStoreManager(String storeManager) {
		this.storeManager = storeManager;
	}
	public String getStoreTaxinfo() {
		return storeTaxinfo;
	}
	public void setStoreTaxinfo(String storeTaxinfo) {
		this.storeTaxinfo = storeTaxinfo;
	}
	public String getStoreType() {
		return storeType;
	}
	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}
	public int getStoreCounter() {
		return storeCounter;
	}
	public void setStoreCounter(int storeCounter) {
		this.storeCounter = storeCounter;
	}
	public String getImageLink() {
		return imageLink;
	}
	public void setImageLink(String imageLink) {
		this.imageLink = imageLink;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
