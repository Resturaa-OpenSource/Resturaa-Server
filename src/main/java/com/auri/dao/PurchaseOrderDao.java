package com.auri.dao;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.Porderentity;



public interface PurchaseOrderDao extends CrudRepository<Porderentity, Integer> {
	
}
