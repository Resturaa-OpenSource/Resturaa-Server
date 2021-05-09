package com.auri.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auri.dao.StaffRepo;
import com.auri.entity.StaffTable;
import com.auri.model.ApiResponse;

@Service
public class StaffService {
	
	@Autowired
	StaffRepo staffRepo;
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public ResponseEntity<StaffTable> addNewStaffService(StaffTable newStaff) {
		
		StaffTable s = staffRepo.save(newStaff);
//		s.set
		return new ResponseEntity<StaffTable>(s,HttpStatus.OK);
	}

	public ResponseEntity<Iterable<StaffTable>> getAllstaff() {
		Iterable<StaffTable> all = staffRepo.findAll();
		return  new ResponseEntity<Iterable<StaffTable>>(all,HttpStatus.OK);
	}

	public ResponseEntity<StaffTable> getByID(Long staffID) {
		return new ResponseEntity<StaffTable>(staffRepo.findByStaffID(staffID).get(),HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteByID(Long staffID) {
		staffRepo.deleteByStaffID(staffID);
		return new ResponseEntity<ApiResponse>( new ApiResponse("200", "success"),HttpStatus.OK);
	}

	public ResponseEntity<Iterable<StaffTable>> getListofstaff() {
		Iterable<StaffTable> st = staffRepo.findAll();
		return new ResponseEntity<Iterable<StaffTable>>(st,HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> updateInfo(StaffTable update) {
		
		StaffTable s = staffRepo.findByStaffID(update.getStaffID()).get();
		
		
		s.setCountry(update.getCountry());
		s.setStaffAddress1(update.getStaffAddress1());
		s.setStaffAddress2(update.getStaffAddress2());
		s.setStaffDOB(update.getStaffDOB());
		s.setStaffEmail(update.getStaffEmail());
		s.setStaffGender(update.getStaffGender());
		s.setStaffID(update.getStaffID());
		s.setStaffJobTittle(update.getStaffJobTittle());
		s.setStaffjoinedDate(update.getStaffjoinedDate());
		s.setStaffName(update.getStaffName());
		s.setStaffPh(update.getStaffPh());
		s.setStaffPincode(update.getStaffPincode());
		s.setState(update.getState());
		
		staffRepo.save(s);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "updated"),HttpStatus.OK);
	}
	public ResponseEntity<ApiResponse> updatePassword(StaffTable update) {
		
		StaffTable staff = staffRepo.findByStaffID(update.getStaffID()).get();
		staff.setStaffUserName(update.getStaffUserName());
		staff.setStaffPassword(passwordEncoder.encode(update.getStaffPassword()));
		staffRepo.save(staff);
		
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "updated"),HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> updatePermission(StaffTable update) {
		StaffTable staff = staffRepo.findByStaffID(update.getStaffID()).get();
		staff.setStaffPermission(update.getStaffPermission());
		System.out.println(update.getStaffPermission());
		staffRepo.save(staff);
		return new ResponseEntity<ApiResponse>( new ApiResponse("200", "updated"),HttpStatus.OK);
	}	

	public ResponseEntity<Boolean> checkUserName(String username) {
	  	
		return new ResponseEntity<Boolean>(staffRepo.existsByStaffUserName(username),HttpStatus.OK);
	}

}
