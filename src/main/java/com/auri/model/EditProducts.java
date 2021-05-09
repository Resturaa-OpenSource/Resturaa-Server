package com.auri.model;

import java.util.List;

import com.auri.entity.ProductTable;
import com.auri.entity.ProductVariants;
//import com.auri.entity.TagTable;

public class EditProducts {

	private ProductTable product;
	private List<ProductVariants> productVariants;
	
	
	
	public ProductTable getProduct() {
		return product;
	}
	public void setProduct(ProductTable product) {
		this.product = product;
	}

	public List<ProductVariants> getProductVariants() {
		return productVariants;
	}
	public void setProductVariants(List<ProductVariants> productVariants) {
		this.productVariants = productVariants;
	}
	

	
}
