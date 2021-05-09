package com.auri.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.auri.entity.TagMappingTable;


public interface TagMapRepo extends CrudRepository<TagMappingTable, Integer>  {
	List<TagMappingTable> findAllByProductID(int productId);
	List<TagMappingTable> findAllByTagID(int tagId);
	List<TagMappingTable> findAllByTagIDAndProductID(int tagID,int productID);
	TagMappingTable findByTagIDAndProductID(int tagID,int productID);
	
	
}
