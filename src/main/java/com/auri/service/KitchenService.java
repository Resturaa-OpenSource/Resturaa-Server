package com.auri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.OrderRepo;
import com.auri.entity.OrderedProductsTable;
import com.auri.entity.OrderTable;

@Service
public class KitchenService {

	@Autowired
	OrderRepo orderRepo;

	public ResponseEntity<Iterable<OrderTable>> getAllOrder(int storeID) {
		List<OrderTable> order = orderRepo.findAllByStoreIDAndStatus(storeID, "KOT");
		List<OrderTable> runningproducts = new ArrayList<>();
		
		System.out.println("running order "+order.size());
		
		
		for (OrderTable oT : order) {
			for(OrderedProductsTable op:oT.getProducts()) {
				if( op.getStatus()==null) {
					runningproducts.add(oT);
					System.out.println(op.getProductName());
					break;
				}
				if( op.getStatus().equals("finished")) {
					System.out.println("finished");
					
				} else {
					System.out.println("else ccond");
					runningproducts.add(oT);
					System.out.println(op.getProductName());
					break;
				}
			}
			System.out.println("--------------");
		}
		
		
		
		return new ResponseEntity<Iterable<OrderTable>>(runningproducts,HttpStatus.OK);
	}

	public ResponseEntity<OrderTable> updateProductStatus(OrderTable order2) {
		
		OrderTable s = orderRepo.save(order2);
		return new ResponseEntity<OrderTable>(s,HttpStatus.OK);	
	}
	
}
