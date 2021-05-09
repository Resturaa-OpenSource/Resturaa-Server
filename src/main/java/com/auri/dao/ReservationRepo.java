package com.auri.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.auri.entity.ReservationTable;

public interface ReservationRepo extends CrudRepository<ReservationTable, Integer> {

	ReservationTable findByOrderId(int orderId);

	ReservationTable findByReservationId(int reservationId);
	

	List<ReservationTable> findAllByStoreId(int storeId);

	List<ReservationTable> findAllByStartTimeBetweenAndStoreId(Date startDate, Date EndDate, int storeID);

	List<ReservationTable> findAllByStartTimeBetween(Date startDate, Date EndDate);

	List<ReservationTable> findAllByEndTimeBetweenAndStoreId(Date startDate, Date EndDate, int storeID);

	List<ReservationTable> findByStartTimeBetweenOrEndTimeBetweenAndStoreId(Date sStartD, Date sEndDate,
			Date eStartDate, Date eEndDate, int storeID);
	
	List<ReservationTable> findAllByStoreIdAndStartTimeAfter(int storeID,Date startDate);
}
