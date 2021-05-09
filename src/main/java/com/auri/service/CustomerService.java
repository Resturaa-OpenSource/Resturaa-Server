package com.auri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.CustomerRepo;
import com.auri.entity.CustomerTable;
import com.auri.model.ApiResponse;
import com.auri.model.CustListForRelation;
import com.auri.model.CustomerList;
import com.auri.model.PurchHistoryList;

@Service
public class CustomerService {

	@Autowired
	CustomerRepo customerRepo;

	public ResponseEntity<Iterable<CustomerTable>> getAllCustomer() {

		Iterable<CustomerTable> c = customerRepo.findAll();
		
		return new ResponseEntity<Iterable<CustomerTable>>(c,HttpStatus.OK);
	}

	public ResponseEntity<CustomerTable> addNewCustomer(CustomerTable customerDetails) {
		System.out.println(customerDetails.getCustImage());
//		SimpleDateFormat formatter = new SimpleDateFormat(
//			      "dd-MM-yyyy HH:mm:ss.SSS");
//		try {
//			Date dob =formatter.parse(formatter.format(customerDetails.getCustDob()));
//			customerDetails.setCustDob(dob);
//			System.out.println("date" + customerDetails.getCustDob() );
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
		CustomerTable s = customerRepo.save(customerDetails);
		
		return new ResponseEntity<CustomerTable>(s,HttpStatus.OK);
	}

	public ResponseEntity<CustomerTable> findCustomerById(int custID) {
		CustomerTable customer = customerRepo.findByCustID(custID);	
//		System.out.println("date  " +customer.getCustDob());
//		
		return new ResponseEntity<CustomerTable>(customer,HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteCustomerById(int custID) {
//		customerRepo.deleteByCustID(custID);
		CustomerTable c = customerRepo.findByCustID(custID);
		customerRepo.delete(c);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "success"),HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> updateCustomerById(CustomerTable newCustomer) {
		if (newCustomer.getCustID() > 0) {
			customerRepo.save(newCustomer);
			return new ResponseEntity<ApiResponse>(new ApiResponse("200", "success"),HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "customer Id not exist on given data"),HttpStatus.OK);
	}

	public ResponseEntity<Iterable<CustomerList>> getCustomerList() {
		List<CustomerList> list = new ArrayList<>() ;
		Iterable<CustomerTable> cutomer = customerRepo.findAll();
		
		for (CustomerTable customerTable : cutomer) {
			CustomerList cust = new CustomerList();
			cust.setCustID(customerTable.getCustID());
			cust.setCustImage(customerTable.getCustImage());
			cust.setCustName(customerTable.getCustName());
			cust.setCustPhone(customerTable.getCustPhone());
			list.add(cust);
		}

		return new ResponseEntity<Iterable<CustomerList>>(list,HttpStatus.OK);
	}

	public ResponseEntity<Iterable<PurchHistoryList>> getCustPurchHistory(int custID) {
		
		return null;
	}

	public ResponseEntity<Iterable<CustListForRelation>> getCustListForSelectRelation() {

		return null;
	}


}
