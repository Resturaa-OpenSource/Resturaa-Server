package com.auri.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "customertable")

public class CustomerTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int custID;
	private String custName;
	private String custPhone;
	private String custEmail;
	private String custSex;
	private String custAddress1;
	private String custAddress2;
	private String custPincode;
	private String custImage;
	private float custCoin;
	
	
	public int getCustID() {
		return custID;
	}
	public void setCustID(int custID) {
		this.custID = custID;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getCustPhone() {
		return custPhone;
	}
	public void setCustPhone(String custPhone) {
		this.custPhone = custPhone;
	}
	public String getCustEmail() {
		return custEmail;
	}
	public void setCustEmail(String custEmail) {
		this.custEmail = custEmail;
	}
	public String getCustSex() {
		return custSex;
	}
	public void setCustSex(String custSex) {
		this.custSex = custSex;
	}
	public String getCustAddress1() {
		return custAddress1;
	}
	public void setCustAddress1(String custAddress1) {
		this.custAddress1 = custAddress1;
	}
	public String getCustAddress2() {
		return custAddress2;
	}
	public void setCustAddress2(String custAddress2) {
		this.custAddress2 = custAddress2;
	}
	public String getCustPincode() {
		return custPincode;
	}
	public void setCustPincode(String custPincode) {
		this.custPincode = custPincode;
	}
	public String getCustImage() {
		return custImage;
	}
	public void setCustImage(String custImage) {
		this.custImage = custImage;
	}
	public float getCustCoin() {
		return custCoin;
	}
	public void setCustCoin(float custCoin) {
		this.custCoin = custCoin;
	}

	}
