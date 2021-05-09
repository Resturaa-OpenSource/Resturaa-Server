package com.auri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.OfferRepo;
import com.auri.entity.OfferTable;
import com.auri.model.ApiResponse;
import com.auri.model.OfferTableList;

@Service
public class OfferService {

	@Autowired
	OfferRepo offerRepo;

	public ResponseEntity<OfferTable> addNewOffer(OfferTable newOffer) {

		OfferTable offer = offerRepo.save(newOffer);
		return new ResponseEntity<OfferTable>(offer,HttpStatus.OK);
	}

	public ResponseEntity<Iterable<OfferTable>> getAllOffer() {

		return new  ResponseEntity<Iterable<OfferTable>>(offerRepo.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<Iterable<OfferTableList>> getOfferList() {
		List<OfferTableList> list = new ArrayList<>();
		Iterable<OfferTable> offer = offerRepo.findAll();
		
		for (OfferTable offerTable : offer) {
			
			OfferTableList table = new OfferTableList();
			table.setOfferTittle(offerTable.getOfferTittle());
			table.setOfferEnable(offerTable.isOfferEnable());
			table.setOfferId(offerTable.getOfferId());
			list.add(table);
			
		}
		
		return new ResponseEntity<Iterable<OfferTableList>>(list,HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> enableOffer(int newofferID, boolean enable) {
		OfferTable offer = offerRepo.findByOfferId(newofferID);
		offer.setOfferEnable(enable);
		offerRepo.save(offer);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "Success"),HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteOffer(int offerID) {

		int i = offerRepo.deleteByofferID(offerID);
		System.out.println(i);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "deleted"),HttpStatus.OK);
		
	}

	public ResponseEntity<OfferTable> getById(int offerID) {
		return new ResponseEntity<OfferTable>(offerRepo.findByOfferId(offerID),HttpStatus.OK);
	}

}
