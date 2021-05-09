package com.auri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.CustomerTable;
import com.auri.model.ApiResponse;
import com.auri.model.CustListForRelation;
import com.auri.model.CustomerList;
import com.auri.model.PurchHistoryList;
import com.auri.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	
	@Autowired
	private CustomerService customerService;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<CustomerTable> addNewCustomer(@RequestBody CustomerTable newCustomer) {
		return customerService.addNewCustomer(newCustomer);
	}

	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<CustomerTable>> getAllUsers() {
		return customerService.getAllCustomer();
	}
	
	@GetMapping(path = "/findById")
	public @ResponseBody ResponseEntity<CustomerTable> findByID(@RequestParam int custID) {
		return customerService.findCustomerById(custID);
	}

	@DeleteMapping(path = "/deleteById")
	public @ResponseBody ResponseEntity<ApiResponse> deleteById(@RequestParam int custID) {
		return customerService.deleteCustomerById(custID);

	}

	@PutMapping(path = "/updateById")
	public @ResponseBody ResponseEntity<ApiResponse> updateById(@RequestBody CustomerTable newCustomer) {
		return customerService.updateCustomerById(newCustomer);

	}

	@GetMapping(path = "/getAllCustomerList")
	public @ResponseBody ResponseEntity<Iterable<CustomerList>> getAllCustomerList() {
		return customerService.getCustomerList();
	}
	
	@GetMapping(path = "/getCustPurchHistory")
	public @ResponseBody ResponseEntity<Iterable<PurchHistoryList>> getCustPurchHistory(@RequestParam int custID) {
		return customerService.getCustPurchHistory(custID);
	}
	
	@GetMapping(path = "/getCustListForRelation")
	public @ResponseBody ResponseEntity<Iterable<CustListForRelation>> getCustListForSelectRelation() {
		return customerService.getCustListForSelectRelation();
	}

}
