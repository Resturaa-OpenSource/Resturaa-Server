package com.auri.model;

import java.util.List;

import com.auri.entity.ProductVariants;
import com.auri.entity.TagTable;

public class ProductView {

	
	private int productID;
	private String image;
	private String productName;
	private String prefix;
	private String productNature;
	private int productPrice;
	private int productTax;
	private int deliveryTime;
	private String productDetails;
	private boolean enable;
	
	private String OfferTittle;
	private String offerDetails;
	private int offerValue;
	private String offerType;
	
	private List<ProductVariants> variants;
	private List<TagTable> tags;
	
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

	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public int getProductTax() {
		return productTax;
	}
	public void setProductTax(int productTax) {
		this.productTax = productTax;
	}
	public int getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getProductDetails() {
		return productDetails;
	}
	public void setProductDetails(String productDetails) {
		this.productDetails = productDetails;
	}
	public boolean isEnable() {
		return enable;
	}
	public void setEnable(boolean enable) {
		this.enable = enable;
	}
	public String getOfferTittle() {
		return OfferTittle;
	}
	public void setOfferTittle(String offerTittle) {
		OfferTittle = offerTittle;
	}
	public String getOfferDetails() {
		return offerDetails;
	}
	public void setOfferDetails(String offerDetails) {
		this.offerDetails = offerDetails;
	}
	public int getOfferValue() {
		return offerValue;
	}
	public void setOfferValue(int offerValue) {
		this.offerValue = offerValue;
	}
	public List<ProductVariants> getVariants() {
		return variants;
	}
	public void setVariants(List<ProductVariants> variants) {
		this.variants = variants;
	}
	public List<TagTable> getTags() {
		return tags;
	}
	public void setTags(List<TagTable> tags) {
		this.tags = tags;
	}
	public String getOfferType() {
		return offerType;
	}
	public void setOfferType(String offerType) {
		this.offerType = offerType;
	}
	public String getPrefix() {
		return prefix;
	}
	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}
	
	
}
