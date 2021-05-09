package com.auri.entity;

import java.util.Date;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class OrderTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int orderNumber;

	private Date orderDate;

	private String staffUsername;

	private int customerId;
	
	private String customerName;
	
	private String customerPhone;

	private int orderTotal;

	private int orderProCount;

	private int deliveryTime;

	private String paymentMode;

	private String status;

	private String orderType;

	private int storeID;
	
	private int deviceID;

	private Date orderEndTime;
	
	private float grandTotal;
	
	@ElementCollection
	@CollectionTable(name = "ordered_products_table", joinColumns = @JoinColumn(name = "order_number"))
	private java.util.List<OrderedProductsTable> products;

	@ElementCollection
	@CollectionTable(name = "selected_table", joinColumns = @JoinColumn(name = "order_number"))
	private java.util.List<SelectedTable> tables;

	
	
	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public String getStaffUsername() {
		return staffUsername;
	}

	public void setStaffUsername(String staffUsername) {
		this.staffUsername = staffUsername;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhone() {
		return customerPhone;
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}

	public int getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(int orderTotal) {
		this.orderTotal = orderTotal;
	}

	public int getOrderProCount() {
		return orderProCount;
	}

	public void setOrderProCount(int orderProCount) {
		this.orderProCount = orderProCount;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public int getStoreID() {
		return storeID;
	}

	public void setStoreID(int storeID) {
		this.storeID = storeID;
	}

	public int getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(int deviceID) {
		this.deviceID = deviceID;
	}

	public Date getOrderEndTime() {
		return orderEndTime;
	}

	public void setOrderEndTime(Date orderEndTime) {
		this.orderEndTime = orderEndTime;
	}

	public float getGrandTotal() {
		return grandTotal;
	}

	public void setGrandTotal(float grandTotal) {
		this.grandTotal = grandTotal;
	}

	public java.util.List<OrderedProductsTable> getProducts() {
		return products;
	}

	public void setProducts(java.util.List<OrderedProductsTable> products) {
		this.products = products;
	}

	public java.util.List<SelectedTable> getTables() {
		return tables;
	}

	public void setTables(java.util.List<SelectedTable> tables) {
		this.tables = tables;
	}

}
