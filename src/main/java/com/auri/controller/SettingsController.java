package com.auri.controller;


import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.BillPage;
import com.auri.entity.DeviceTable;
import com.auri.model.ApiResponse;
import com.auri.service.SettingService;
import com.smattme.MysqlExportService;


@RestController
@RequestMapping("/api/settings")
public class SettingsController {
	
	@Autowired
	SettingService service;
	
	@GetMapping(path = "/backup")
	public @ResponseBody ResponseEntity<String> backUp() {
		Properties properties = new Properties();
		properties.setProperty(MysqlExportService.DB_NAME, "pos");
		properties.setProperty(MysqlExportService.DB_USERNAME, "Rintu");
		properties.setProperty(MysqlExportService.DB_PASSWORD, "r18000018r");
		//properties relating to email config
		properties.setProperty(MysqlExportService.PRESERVE_GENERATED_ZIP, "true");
		//set the outputs temp dir
		String f = new File("upload-dir").getPath();
		System.out.println(f);
		properties.setProperty(MysqlExportService.TEMP_DIR, f);
		System.out.println(MysqlExportService.TEMP_DIR);
		
//		properties.setProperty(Paths.get("upload-dir").toString(), f);
		MysqlExportService mysqlExportService = new MysqlExportService(properties);
		try {
			mysqlExportService.export();
		} catch (ClassNotFoundException | IOException | SQLException e) {
			e.printStackTrace();
		}
		File file = mysqlExportService.getGeneratedZipFile();
		System.out.println("G file   :"+file);
//		String sql = new String(Files.readAllBytes(Paths.get("path/to/sql/dump/file.sql")));
//		boolean res = MysqlImportService.builder()
//		        .setDatabase("database-name")
//		        .setSqlString(sql)
//		        .setUsername("root")
//		        .setPassword("root")
//		        .setDeleteExisting(true)
//		        .setDropExisting(true)
//		        .importDatabase();
//		
		
		return new ResponseEntity<String>(file.getName(),HttpStatus.OK);
	}
	
	@GetMapping(path = "/registerDevice")
	public ResponseEntity<DeviceTable> registerDevice(@RequestParam int storeID){
		return service.registerDevice(storeID);
		
	}
	@GetMapping(path= "/checkDevice")
	public ResponseEntity<ApiResponse> checkDevice(@RequestParam UUID deviceID){
		return service.checkDevice(deviceID);
		
	}
	
	@PostMapping(path = "/addBill")
	public @ResponseBody ResponseEntity<BillPage> addBill(@RequestBody BillPage bill){
		return service.addBill(bill);
	}
	
	@GetMapping(path = "/getBill")
	public @ResponseBody ResponseEntity<BillPage> getBill(@RequestParam int storeID){
		return service.getBill(storeID);
	}
	
	@DeleteMapping(path = "/deleteBill")
	public @ResponseBody ResponseEntity<BillPage> deleteBill(@RequestParam int storeID){
		return service.deleteBill(storeID);
	}
}
