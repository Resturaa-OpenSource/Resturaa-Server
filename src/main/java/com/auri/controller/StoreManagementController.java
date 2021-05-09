package com.auri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.StoreSeatTable;
import com.auri.entity.StoreTable;
import com.auri.model.ApiResponse;
import com.auri.model.StoreList;
import com.auri.model.ViewStoreAndProducts;
import com.auri.service.StoreService;

@RestController
@RequestMapping("/api/StoreManagement")
public class StoreManagementController {

	@Autowired
	private StoreService storeService;

	@PostMapping(path = "/signup")
	public @ResponseBody ResponseEntity<StoreTable> signup(@RequestBody StoreTable signup){
		return storeService.signup(signup);
	}
	@PostMapping(path = "/signin")
	public @ResponseBody ResponseEntity<StoreTable> signin(@RequestBody StoreTable signin){
		return storeService.signin(signin);
	}
	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<StoreTable> addNewStore(@RequestBody StoreTable newstore) {
		return storeService.addnewStore(newstore);
	}
	@DeleteMapping(path = "/deleteStore")
	public @ResponseBody ResponseEntity<ApiResponse> deleteStore(@RequestParam int storeID){
		return storeService.deleteStore(storeID);
	}

	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<StoreTable>> getAllStore() {
		return storeService.getAll();
	}

	@GetMapping(path = "/addProduct")
	public @ResponseBody ResponseEntity<ViewStoreAndProducts> addProduct(@RequestParam int storeID, int productID) {
		return storeService.addProducts(storeID, productID);
	}

	@GetMapping(path = "/enableProduct")
	public @ResponseBody ResponseEntity<ApiResponse> enableStore(@RequestParam int storeID, int productID,
			boolean enable) {
		return storeService.enableProduct(storeID, productID, enable);
	}

	@GetMapping(path = "/viewStoredetails")
	public @ResponseBody ResponseEntity<ViewStoreAndProducts> viewStoredetails(@RequestParam int storeID) {
		return storeService.viewStoredetails(storeID);
	}

	@GetMapping(path = "/deleteProductFromStore")
	public @ResponseBody ResponseEntity<ApiResponse> deleteProductFromStore(@RequestParam int storeID, int productID) {
		return storeService.deleteProductFromStore(storeID, productID);
	}

	@GetMapping(path = "/StoreListView")
	public @ResponseBody ResponseEntity<List<StoreList>> storeListView() {
		return storeService.storeListView();
	}

	@PutMapping(path = "/addTable")
	public @ResponseBody ResponseEntity<StoreSeatTable> addTable(@RequestBody StoreSeatTable newTable) {
		return storeService.addNewTable(newTable);
	}

	@DeleteMapping(path = "/deleteTable")
	public @ResponseBody ResponseEntity<ApiResponse> deleteTable(@RequestParam int tableId) {
		return storeService.deleteTable(tableId);
	}

	@GetMapping(path = "/getStoreTableDetails")
	public @ResponseBody ResponseEntity<Iterable<StoreSeatTable>> getAllStoreSeatTable(@RequestParam int storeID) {
		return storeService.getAllStoreTable(storeID);
	}
	@GetMapping(path ="/getAllProductIds")
	public @ResponseBody ResponseEntity<List<Integer>> getAllProductById(@RequestParam int storeID){
		return storeService.getAllproductIds(storeID);
	}
	@GetMapping(path = "/getTable")
	public @ResponseBody ResponseEntity<StoreSeatTable> getTable(@RequestParam int tableId) {
		return storeService.getStoreTable(tableId);
	}
	
	@PutMapping(path = "/editStoreDetails")
	public @ResponseBody ResponseEntity<StoreTable> editStoreDetails(@RequestParam int storeID) {
		return storeService.editStoreDetails(storeID);
	}


}
