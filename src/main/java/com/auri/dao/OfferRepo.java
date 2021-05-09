package com.auri.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.auri.entity.OfferTable;

public interface OfferRepo extends CrudRepository<OfferTable, Integer>  {
	OfferTable findByOfferId(int offerid);
	OfferTable findByOfferIdAndOfferEnable(int offerId,boolean  offerEnable);
	

	@Modifying
	@Transactional
	@Query("delete from OfferTable u where u.offerId = ?1")
	int deleteByofferID(int offer);
	
	
	
}
