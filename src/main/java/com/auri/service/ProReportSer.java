package com.auri.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.OrderRepo;
import com.auri.entity.OrderTable;
import com.auri.entity.OrderedProductsTable;
import com.auri.model.ProductReport;
import com.google.gson.Gson;

@Service
public class ProReportSer {

	@Autowired
	OrderRepo orderRepo;

	@SuppressWarnings("unchecked")
	public ResponseEntity<Iterator<ProductReport>> getProductReport(Date startDate, Date endDate, int storeID) {
		List<ProductReport> prd = new ArrayList<ProductReport>();
		System.out.println(new Gson().toJson(startDate, Date.class));
		List<OrderTable> ot = orderRepo.findAllByOrderDateBetweenAndStoreID(startDate, endDate, storeID);

		for (OrderTable orderTable : ot) {

			for (OrderedProductsTable pro : orderTable.getProducts()) {

				
				String productName = pro.getProductName();
				int value = pro.getQuantity();
				ProductReport prt = new ProductReport(value,productName);
				System.out.println(productName + " - " + value);

				
			
				
				
//				Iterator<ProductReport> list = prd.iterator();
//				while (list.hasNext()) {
//					ProductReport p = list.next();
//
//					if (p.getProductName().equalsIgnoreCase(productName)) {
//						value = value + p.getSellingCount();
////						System.out.print(p.getProductName());
////						System.out.println(" -  " + value);
//					}
//					System.out.println();
//				}
////				
//				prt.setProductName(productName);
//				prt.setSellingCount(value);
				prd.add(prt);
				
				

			}
		}

		Iterator<ProductReport> list = prd.iterator();

		while (list.hasNext()) {
			
			ProductReport p = list.next();
			System.out.print(p.getProductName());
			System.out.println(" - " + p.getSellingCount());
			System.out.println("-----------------------------");
		
		}

		return new ResponseEntity<Iterator<ProductReport>>(prd.iterator(), HttpStatus.OK);
	}
}
