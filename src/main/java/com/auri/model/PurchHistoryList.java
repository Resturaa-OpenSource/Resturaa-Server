package com.auri.model;

import java.util.Date;

public class PurchHistoryList {

	private int purchaseID;
	private Date date;
	private int Amount;
	
	public int getPurchaseID() {
		return purchaseID;
	}
	public void setPurchaseID(int purchaseID) {
		this.purchaseID = purchaseID;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getAmount() {
		return Amount;
	}
	public void setAmount(int amount) {
		Amount = amount;
	}
}
