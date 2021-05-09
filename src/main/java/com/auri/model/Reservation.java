package com.auri.model;

import com.auri.entity.OrderTable;
import com.auri.entity.ReservationTable;

public class Reservation {

	private ReservationTable resDetails;
	private OrderTable orderTable;
	
	public ReservationTable getResDetails() {
		return resDetails;
	}
	public void setResDetails(ReservationTable resDetails) {
		this.resDetails = resDetails;
	}
	public OrderTable getOrderTable() {
		return orderTable;
	}
	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}
	
}
