

package com.auri.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "store_seat_table")
public class StoreSeatTable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int tableId;
	private int storeId;
	private String tableName;
	private int seatCount;
	private int seatOccupied;
	
	public int getTableId() {
		return tableId;
	}
	public void setTableId(int tableNumber) {
		this.tableId = tableNumber;
	}
	public int getStoreId() {
		return storeId;
	}
	public void setStoreId(int storeId) {
		this.storeId = storeId;
	}
	public int getSeatCount() {
		return seatCount;
	}
	public void setSeatCount(int seatCount) {
		this.seatCount = seatCount;
	}
	public int getSeatOccupied() {
		return seatOccupied;
	}
	public void setSeatOccupied(int seatOccupied) {
		this.seatOccupied = seatOccupied;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	
}
