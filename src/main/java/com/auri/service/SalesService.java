package com.auri.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.OrderRepo;
import com.auri.entity.OrderTable;
import com.auri.model.SalesOutput;

@Service
public class SalesService {
	
	
	@Autowired
	OrderRepo orderRepo;

	public ResponseEntity<List<SalesOutput>> getSalesReport(Date startDate, Date endDate, int storeID) {
		List<SalesOutput> slist = new ArrayList<>();
		
		List<OrderTable> ord = orderRepo.findAllByOrderDateBetweenAndStoreID(startDate, endDate, storeID);
		for(OrderTable so : ord) {
		
		SalesOutput sl = new SalesOutput();
		sl.setOrderNumber(so.getOrderNumber());
		sl.setDate(so.getOrderEndTime());
		sl.setProductQuantity(so.getOrderProCount());
		sl.setTotalAmount(so.getGrandTotal());
		sl.setStatus(so.getStatus());
		slist.add(sl);
	}
		return new ResponseEntity<List<SalesOutput>>(slist,HttpStatus.OK);
	}
}
