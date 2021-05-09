package com.auri.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.auri.entity.ProductTable;

public interface ProductRepo extends CrudRepository<ProductTable, Integer> {

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM ProductTable c WHERE c.itemCode = :item")
    boolean existsByName(@Param("item") int i);
	
	
	@Modifying
	@Transactional
	@Query("delete from ProductTable u where u.itemCode = ?1")
	int deleteByitemCode(String itemcode);
	
	@Query( "select o from ProductTable o where o.itemCode in :ids" )
	List<ProductTable> findAllByItemCode(@Param("ids") List<Integer> itemcode);
	
	ProductTable findByItemCode(int itemCode);
	
}
