package com.auri.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auri.entity.StoreSeatTable;

public interface StoreSeatRepo extends CrudRepository<StoreSeatTable, Integer> {
//	
	@Modifying
	@Transactional
	@Query("delete from StoreSeatTable u where u.tableId = ?1")
	int deleteBytableId(int tableId);
	
	List<StoreSeatTable> findAllByStoreId(int storeId);
	
	StoreSeatTable findByTableId(int tableId);
	List<StoreSeatTable> findAllByTableNameAndStoreId(String tableName,int storeID);
	
}
