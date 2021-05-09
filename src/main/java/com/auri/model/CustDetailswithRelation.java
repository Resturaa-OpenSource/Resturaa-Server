package com.auri.model;

import java.util.Date;
import java.util.List;

public class CustDetailswithRelation {

	private int 	custID;
	private String 	custName;
	private String 	custPhone;
	private String 	custEmail;
	private String 	custSex;
	private String 	custAddress1;
	private String 	custAddress2;
	private String 	custAddress3;
	private Date 	custDob;
	private String 	custPincode;
	private String 	 custImage;
	private int 	 custCoin;	
	private List<RelationDetails> customerRelation;
	
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
	public Date getCustDob() {
		return custDob;
	}
	public void setCustDob(java.util.Date date) {
		this.custDob = date;
	}
	public String getCustPincode() {
		return custPincode;
	}
	public void setCustPincode(String string) {
		this.custPincode = string;
	}
	public String getCustImage() {
		return custImage;
	}
	public void setCustImage(String custImage) {
		this.custImage = custImage;
	}
	public int getCustCoin() {
		return custCoin;
	}
	public void setCustCoin(int custCoin) {
		this.custCoin = custCoin;
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
	public String getCustAddress3() {
		return custAddress3;
	}
	public void setCustAddress3(String custAddress3) {
		this.custAddress3 = custAddress3;
	}
	public List<RelationDetails> getCustomerRelation() {
		return customerRelation;
	}
	public void setCustomerRelation(List<RelationDetails> customerRelation) {
		this.customerRelation = customerRelation;
	}
}
