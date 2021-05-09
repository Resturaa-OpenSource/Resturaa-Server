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


import com.auri.entity.ProductTable;
import com.auri.entity.ProductVariants;
import com.auri.entity.StoreProductTable;
import com.auri.model.ApiResponse;
import com.auri.model.EditProducts;

import com.auri.model.ProductView;
import com.auri.model.ProductsListTable;
import com.auri.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<ProductTable> addnewproduct(@RequestBody EditProducts newProduct) {
		return productService.addNewProduct(newProduct);
	}
	
	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<ProductTable>> getAllProducts() {
		
		return productService.getAllProducts();
	}
	@GetMapping(path = "/getAllStoreProducts")
	public @ResponseBody ResponseEntity<List<ProductsListTable>> getAllStoreProducts(@RequestParam int StoreID) {	
		return productService.getAllStoreProducts(StoreID);
	}
	@GetMapping(path = "/viewProduct")
	public @ResponseBody ResponseEntity<ProductView> viewProduct(@RequestParam int StoreID,int productID) {
		
		return productService.viewProduct(StoreID,productID);
	}
	
	@GetMapping(path = "/editProduct")
	public @ResponseBody ResponseEntity<EditProducts> editProduct(@RequestParam int productID) {
		return productService.editProduct(productID);
	}
	
	@PutMapping(path = "/updateProduct")
	public @ResponseBody ResponseEntity<ProductTable> updateProduct(@RequestBody EditProducts product) {
		return productService.updateProduct(product);
		
	}
	
	@GetMapping(path = "/enableProduct")
	public @ResponseBody ResponseEntity<StoreProductTable> enableProduct(@RequestParam int storeID,int productID,boolean enable) {
		return productService.enableProduct(storeID,productID,enable);
		
	}
	@GetMapping(path = "/applyOffer")
	public @ResponseBody ResponseEntity<ApiResponse> applyOffer(@RequestParam int storeID,int productID,int offerId) {
		System.out.println("apply ofer"+productID +"  "+offerId);
		return productService.applyOffer(storeID,productID,offerId);
	}
	@DeleteMapping(path = "/delete")
	public @ResponseBody ResponseEntity<ApiResponse> delete(@RequestParam int productID) {
		return productService.delete(productID);
	}
	@DeleteMapping(path = "/deleteByPVId")
	public @ResponseBody ResponseEntity<ProductVariants> deleteByPVId(@RequestParam int productVariantID){
		return productService.deleteByPVId(productVariantID);
	}
	
}
