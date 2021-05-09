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

import com.auri.entity.StaffTable;
import com.auri.model.ApiResponse;
import com.auri.service.StaffService;

@RestController
@RequestMapping("/api/staffManagement")
public class StaffManagementController {

	@Autowired
	private StaffService staffService;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<StaffTable> addNewUser(@RequestBody StaffTable newStaff) {
		return staffService.addNewStaffService(newStaff);
	}
	
	@GetMapping (path = "/checkUsername")
	public @ResponseBody ResponseEntity<Boolean>checkUserName(@RequestParam String username){
		return staffService.checkUserName(username);
	}
	
	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<StaffTable>> getAll(){
		return staffService.getAllstaff();
	}
	
	@GetMapping(path = "/getByID")
	public @ResponseBody ResponseEntity<StaffTable> getStaffByID(@RequestParam Long staffID){
		return staffService.getByID(staffID);
	}
	
	@DeleteMapping(path = "/deleteByID")
	public @ResponseBody ResponseEntity<ApiResponse> deleteByID(@RequestParam Long staffID){
		return staffService.deleteByID(staffID);
	}
	
	@GetMapping(path = "/getListofstaff")
	public @ResponseBody ResponseEntity<Iterable<StaffTable>> getListofstaff(){
		return staffService.getListofstaff();
	}
	@PutMapping(path = "/updatePassword")
	public @ResponseBody ResponseEntity<ApiResponse> updatePassword(@RequestBody StaffTable update){
		return staffService.updatePassword(update);
	}
	
	@PutMapping(path = "/updateInfo")
	public @ResponseBody ResponseEntity<ApiResponse> updateInfo(@RequestBody StaffTable update){
		return staffService.updateInfo(update);
	}
	
	@PutMapping(path = "/updatepermission")
	public @ResponseBody ResponseEntity<ApiResponse> updatePermission(@RequestBody StaffTable update){
		return staffService.updatePermission(update);
	}
	
	
}
