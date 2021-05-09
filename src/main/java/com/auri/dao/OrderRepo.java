package com.auri.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.OrderTable;



public interface OrderRepo extends CrudRepository<OrderTable, Integer> {
	
	List<OrderTable> findAllByStoreIDAndStatus(int storeID,String status);
	
	OrderTable findByOrderNumber(int orderNumber);
	List<OrderTable> findAllByCustomerId(int customerId);

	List<OrderTable> findAllByStoreID(int storeID);
	List<OrderTable> findAllByOrderDateBetweenAndStoreID(Date startDate, Date endDate,int storeID);


	
	

}
