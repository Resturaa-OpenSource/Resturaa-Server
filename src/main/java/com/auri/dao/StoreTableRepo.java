package com.auri.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auri.entity.StoreTable;



public interface StoreTableRepo extends CrudRepository<StoreTable, Integer>  {
	
	StoreTable findByStoreId(int storeId);
	
	@Modifying
	@Transactional
	@Query("delete from StoreTable u where u.storeId = ?1")
	int deleteBytableId(int tableId);

	StoreTable findByStoreIdAndPassword(int storeId, String password);
	
}
