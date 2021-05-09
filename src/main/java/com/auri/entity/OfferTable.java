package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "offer_table")
public class OfferTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int offerId;
	
	private String offerTittle;
	
	private float offerValue;
	
	private String offerDetails;
	
	private boolean  offerEnable;

	private int storeID;
	

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferTittle() {
		return offerTittle;
	}

	public void setOfferTittle(String offerTittle) {
		this.offerTittle = offerTittle;
	}

	public float getOfferValue() {
		return offerValue;
	}

	public void setOfferValue(float offerValue) {
		this.offerValue = offerValue;
	}

	public String getOfferDetails() {
		return offerDetails;
	}

	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}

	public boolean isOfferEnable() {
		return offerEnable;
	}

	public void setOfferEnable(boolean offerEnable) {
		this.offerEnable = offerEnable;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}
	
	
}
