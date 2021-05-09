package com.auri.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.BillPage;

public interface SettingRepo extends CrudRepository<BillPage, Integer> {

	List<BillPage> findAllByStoreID(int storeID);

	BillPage findByStoreID(int storeID);

}
