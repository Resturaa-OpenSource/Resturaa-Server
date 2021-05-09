package com.auri.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.IngredientMapping;


public interface IngredientMappingDao  extends CrudRepository<IngredientMapping, Integer> {
	
	
	List<IngredientMapping> findAllByProductIDAndProductVarientID(int productID,int productVarientID);
	
	
	
}
