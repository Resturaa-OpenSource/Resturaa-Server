package com.auri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.OfferTable;
import com.auri.model.ApiResponse;
import com.auri.model.OfferTableList;
import com.auri.service.OfferService;

@RestController
@RequestMapping("/api/OfferManagement")
public class OfferManagementController {

	@Autowired
	OfferService offerService;
	
	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<OfferTable> addNewOffer(@RequestBody OfferTable newOffer) {
		return offerService.addNewOffer(newOffer);
	}
	@GetMapping(path = "/getById")
	public @ResponseBody ResponseEntity<OfferTable> getByID(@RequestParam int offerID){
		return offerService.getById(offerID);	
	}
	@GetMapping(path = "/enableOffer")
	public @ResponseBody ResponseEntity<ApiResponse> enableOffer(@RequestParam int offerID,boolean enable) {
		return offerService.enableOffer(offerID,enable);
	}
	
	@DeleteMapping(path = "/deleteOffer")
	public @ResponseBody ResponseEntity<ApiResponse> deleteOffer(@RequestParam int offerID) {
		return offerService.deleteOffer(offerID);
	}
	
	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<OfferTable>> getAllOffers() {
		return offerService.getAllOffer();	
	}
	
	@GetMapping(path = "/getOfferList")
	public @ResponseBody ResponseEntity<Iterable<OfferTableList>> getOfferList() {
		return offerService.getOfferList();
	}
	
}
