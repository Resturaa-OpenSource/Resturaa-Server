package com.auri.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.ProductVariants;

public interface ProductVariantRepo extends CrudRepository<ProductVariants, Integer>  {
	ProductVariants findByProductID(int productId);

	List<ProductVariants> findAllByProductID(int productID);

	ProductVariants findByProductVariantID(int productVariantID);
}
