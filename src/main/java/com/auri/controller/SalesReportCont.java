package com.auri.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.model.SalesOutput;
import com.auri.service.SalesService;

@RestController
@RequestMapping("/api/salesReport")
public class SalesReportCont {
	
	@Autowired
	SalesService salesService;
	
	@GetMapping(path = "/getSalesReport")
	public @ResponseBody ResponseEntity<List<SalesOutput>> getSalesReport(@RequestParam Date startDate, Date endDate, int storeID){
		return salesService.getSalesReport(startDate,endDate,storeID);
	}

}
