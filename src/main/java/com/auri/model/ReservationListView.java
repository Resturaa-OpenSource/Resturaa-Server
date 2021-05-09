package com.auri.model;

import com.auri.entity.CustomerTable;
import com.auri.entity.OrderTable;
import com.auri.entity.ReservationTable;

public class ReservationListView {

	private OrderTable orderTable;
	private ReservationTable reservationTable;

	public OrderTable getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(OrderTable orderTable) {
		this.orderTable = orderTable;
	}

	public ReservationTable getReservationTable() {
		return reservationTable;
	}

	public void setReservationTable(ReservationTable reservationTable) {
		this.reservationTable = reservationTable;
	}

}

