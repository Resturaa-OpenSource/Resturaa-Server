package com.auri.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.auri.entity.StoreProductTable;

public interface ProductsInStoreRepo extends CrudRepository<StoreProductTable, Integer> {
	
	List<StoreProductTable> findAllByStoreID(int storeID);
	List<StoreProductTable> findAllByProductID(int productID );
	StoreProductTable findByStoreIDAndProductID(int storeID,int productID);
	
	@Query("SELECT u FROM StoreProductTable u where u.productID = :product  and u.storeID = :store")
	List<StoreProductTable>  findByproductIdAndStoreID(@Param("product") int product_ID,@Param("store") int store_ID);
	
	@Query("SELECT u FROM StoreProductTable u where  u.storeID = :store and u.enable = :enable")
	List<StoreProductTable>  findAllBystoreIdAndEnabled(@Param("store") int store_ID,@Param("enable") boolean enable);
	
	@Modifying
	@Transactional
	@Query("delete from StoreProductTable u where u.productEnableID = ?1")
	int deleteByproductEnableID(int id);
	
	
	
}
