package com.auri.controller;

import java.util.Date;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.model.ProductReport;
import com.auri.service.ProReportSer;

@RestController
@RequestMapping("/api/productReport")
public class ProReportCont {
	
	@Autowired
	ProReportSer prs;
	
	@GetMapping(path = "/getProductReport")
	public @ResponseBody ResponseEntity<Iterator<ProductReport>> getProductReport(@RequestParam Date startDate, Date endDate, int storeID){
		return prs.getProductReport(startDate,endDate,storeID);
	}

}
