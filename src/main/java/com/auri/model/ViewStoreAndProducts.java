package com.auri.model;

import java.util.List;

public class ViewStoreAndProducts {

	private String storeName;
	private int storeID;
	private String storeLocation;
	private String storeType;
	private String taxInfo;
	private int numberOfSellingProducts;
	private String storeImage;
	
	private List<ProductsListTable> products;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public String getStoreType() {
		return storeType;
	}

	public void setStoreType(String storeType) {
		this.storeType = storeType;
	}

	public String getTaxInfo() {
		return taxInfo;
	}

	public void setTaxInfo(String taxInfo) {
		this.taxInfo = taxInfo;
	}

	public int getNumberOfSellingProducts() {
		return numberOfSellingProducts;
	}

	public void setNumberOfSellingProducts(int numberOfSellingProducts) {
		this.numberOfSellingProducts = numberOfSellingProducts;
	}

	public List<ProductsListTable> getProducts() {
		return products;
	}

	public void setProducts(List<ProductsListTable> products) {
		this.products = products;
	}

	public String getStoreImage() {
		return storeImage;
	}

	public void setStoreImage(String storeImage) {
		this.storeImage = storeImage;
	}

	public String getStoreLocation() {
		return storeLocation;
	}

	public void setStoreLocation(String storeLocation) {
		this.storeLocation = storeLocation;
	}
}
