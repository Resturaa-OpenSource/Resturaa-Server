package com.auri.model;

public class ProductsListTable {

	private int productID;
	private String image;
	private String productName;
	private String productNature;
	private String offerName;
	private float productPrice;
	private float productTax;
	private boolean enable;

	
	public int getProductID() {
		return productID;
	}

	public void setProductID(int productID) {
		this.productID = productID;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String string) {
		this.image = string;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductNature() {
		return productNature;
	}

	public void setProductNature(String productNature) {
		this.productNature = productNature;
	}

	public String getOfferName() {
		return offerName;
	}

	public void setOfferName(String offerName) {
		this.offerName = offerName;
	}

	public float getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(float productPrice) {
		this.productPrice = productPrice;
	}

	public float getProductTax() {
		return productTax;
	}

	public void setProductTax(float productTax) {
		this.productTax = productTax;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}
}
