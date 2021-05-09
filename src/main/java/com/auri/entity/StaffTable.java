package com.auri.entity;


import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StaffTable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long staffID;
	private String staffName;
	private String staffPh;
	private String staffEmail;
	private String staffImage;
	
	private String staffAddress1;
	private String staffAddress2;
	private String state;
	private String country;
	private int staffPincode;
	
	private String staffJobTittle;
	private String staffGender;
	private Date staffDOB;
	private Date staffjoinedDate;
	private String staffPermission;
	private String staffUserName;
	private String staffPassword;

	
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
	public String getStaffAddress() {
		return staffAddress1;
	}
	public void setStaffAddress(String staffAddress) {
		this.staffAddress1 = staffAddress;
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
	public String getStaffPermission() {
		return staffPermission;
	}
	public void setStaffPermission(String staffPermission) {
		this.staffPermission = staffPermission;
	}
	public String getStaffPassword() {
		return staffPassword;
	}
	public void setStaffPassword(String staffPassword) {
		this.staffPassword = staffPassword;
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
	public Date getStaffjoinedDate() {
		return staffjoinedDate;
	}
	public void setStaffjoinedDate(Date staffjoinedDate) {
		this.staffjoinedDate = staffjoinedDate;
	}
	public String getStaffUserName() {
		return staffUserName;
	}
	public void setStaffUserName(String staffUserName) {
		this.staffUserName = staffUserName;
	}
	public String getStaffImage() {
		return staffImage;
	}
	public void setStaffImage(String staffImage) {
		this.staffImage = staffImage;
	}

}
