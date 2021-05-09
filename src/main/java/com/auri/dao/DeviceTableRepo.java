package com.auri.dao;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.DeviceTable;

public interface DeviceTableRepo extends CrudRepository<DeviceTable, Integer> {
	DeviceTable findByUid(UUID uid);
	List<DeviceTable> findAllByStoreID(int storeID);
}
