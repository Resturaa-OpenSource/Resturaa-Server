package com.auri.model;

import java.util.Date;

public class StaffList {

	private Long staffID;
	private String staffName;
	private String staffPh;
	private String staffEmail;
	
	private String staffAddress1;
	private String staffAddress2;
	private String state;
	private String country;
	private int staffPincode;
	
	private String staffJobTittle;
	private String staffGender;
	private Date staffDOB;
	private Date staffjoinedDate;
	
	
	public Long getStaffID() {
		return staffID;
	}
	public void setStaffID(Long staffID) {
		this.staffID = staffID;
	}
	public String getStaffName() {
		return staffName;
	}
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	public String getStaffPh() {
		return staffPh;
	}
	public void setStaffPh(String staffPh) {
		this.staffPh = staffPh;
	}
	public String getStaffEmail() {
		return staffEmail;
	}
	public void setStaffEmail(String staffEmail) {
		this.staffEmail = staffEmail;
	}
	public String getStaffAddress1() {
		return staffAddress1;
	}
	public void setStaffAddress1(String staffAddress1) {
		this.staffAddress1 = staffAddress1;
	}
	public String getStaffAddress2() {
		return staffAddress2;
	}
	public void setStaffAddress2(String staffAddress2) {
		this.staffAddress2 = staffAddress2;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getStaffPincode() {
		return staffPincode;
	}
	public void setStaffPincode(int staffPincode) {
		this.staffPincode = staffPincode;
	}
	public String getStaffJobTittle() {
		return staffJobTittle;
	}
	public void setStaffJobTittle(String staffJobTittle) {
		this.staffJobTittle = staffJobTittle;
	}
	public String getStaffGender() {
		return staffGender;
	}
	public void setStaffGender(String staffGender) {
		this.staffGender = staffGender;
	}
	public Date getStaffDOB() {
		return staffDOB;
	}
	public void setStaffDOB(Date staffDOB) {
		this.staffDOB = staffDOB;
	}
	public Date getStaffjoinedDate() {
		return staffjoinedDate;
	}
	public void setStaffjoinedDate(Date staffjoinedDate) {
		this.staffjoinedDate = staffjoinedDate;
	}

}
