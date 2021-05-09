package com.auri.model;

import java.util.List;

import com.auri.entity.OfferTable;
import com.auri.entity.ProductTable;
import com.auri.entity.ProductVariants;
import com.auri.entity.TagMappingTable;

public class ProductDetailsPos {

	private ProductTable product;
	private List<TagMappingTable> tagIds;
	private List<ProductVariants> productVariants;
	private OfferTable offer;
	
	public ProductTable getProduct() {
		return product;
	}
	public void setProduct(ProductTable product) {
		this.product = product;
	}
	public List<TagMappingTable> getTagIds() {
		return tagIds;
	}
	public void setTagIds(List<TagMappingTable> tagIds) {
		this.tagIds = tagIds;
	}
	public List<ProductVariants> getProductVariants() {
		return productVariants;
	}
	public void setProductVariants(List<ProductVariants> productVariants) {
		this.productVariants = productVariants;
	}
	public OfferTable getOffer() {
		return offer;
	}
	public void setOffer(OfferTable offer) {
		this.offer = offer;
	}
	
	
}
