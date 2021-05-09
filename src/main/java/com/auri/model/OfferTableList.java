package com.auri.model;

public class OfferTableList {

	private int offerId;
	private String offerTittle;
	private String offerType;
	private int offerValue;
	private boolean offerEnable;
	public int getOfferId() {
		return offerId;
	}
	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}

	public int getOfferValue() {
		return offerValue;
	}
	public void setOfferValue(int offerValue) {
		this.offerValue = offerValue;
	}
	public boolean isOfferEnable() {
		return offerEnable;
	}
	public void setOfferEnable(boolean offerEnable) {
		this.offerEnable = offerEnable;
	}
	public String getOfferTittle() {
		return offerTittle;
	}
	public void setOfferTittle(String offerTittle) {
		this.offerTittle = offerTittle;
	}
	
}

