package com.auri.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.auri.dao.DeviceTableRepo;
import com.auri.dao.SettingRepo;
import com.auri.dao.StaffRepo;
import com.auri.dao.StoreTableRepo;
import com.auri.entity.BillPage;
import com.auri.entity.DeviceTable;
import com.auri.entity.StaffTable;
import com.auri.entity.StoreTable;
import com.auri.model.ApiResponse;
import com.auri.model.RoleName;

@Service
public class SettingService {

	@Autowired
	DeviceTableRepo deviceRepo;

	@Autowired
	StaffRepo staffRepo;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	StoreTableRepo store;

	@Autowired
	SettingRepo sett;

	public ResponseEntity<DeviceTable> registerDevice(int storeID) {

		if (!staffRepo.existsByStaffUserName("ADMIN")) {
			System.out.println("Rgister device");
			StaffTable staff = new StaffTable();
			staff.setStaffUserName("admin");
			staff.setStaffPassword(passwordEncoder.encode("admin"));
			staff.setStaffEmail("admin");
			staff.setStaffName("admin");
			staff.setStaffJobTittle(RoleName.ROLE_ADMIN.toString());
			staffRepo.save(staff);
		}
		StoreTable s = store.findByStoreId(storeID);

		if (s == null) {
			return new ResponseEntity<DeviceTable>(new DeviceTable(), HttpStatus.BAD_REQUEST);
		}

		DeviceTable d = new DeviceTable();

		d.setStoreID(storeID);
		d.setDate(new java.util.Date());
		DeviceTable l = deviceRepo.save(d);
		return new ResponseEntity<DeviceTable>(l, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> checkDevice(UUID deviceID) {
		System.out.println("  " + deviceID);
		DeviceTable device = deviceRepo.findByUid(deviceID);
		if (device != null) {
			return new ResponseEntity<ApiResponse>(new ApiResponse("200", "exists"), HttpStatus.OK);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "not exists"), HttpStatus.OK);
	}

	public ResponseEntity<BillPage> addBill(BillPage bill) {
		BillPage b = new BillPage();
		if (sett.findByStoreID(bill.getStoreID()) != null) {
			b = sett.findByStoreID(bill.getStoreID());
		}
		b.setImageLink(bill.getImageLink());
		b.setMessage(bill.getMessage());
		b.setStoreID(bill.getStoreID());
		return new ResponseEntity<BillPage>(sett.save(b), HttpStatus.OK);
	}

	public ResponseEntity<BillPage> getBill(int storeID) {
		BillPage bp = sett.findByStoreID(storeID);
		return new ResponseEntity<BillPage>(bp, HttpStatus.OK);
	}

	public ResponseEntity<BillPage> deleteBill(int storeID) {
		BillPage bil = sett.findByStoreID(storeID);
		sett.delete(bil);

		return new ResponseEntity<BillPage>(bil, HttpStatus.OK);
	}
}
