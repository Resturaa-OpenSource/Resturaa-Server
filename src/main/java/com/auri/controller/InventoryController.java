package com.auri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.IngredientMapping;
import com.auri.entity.Ingredients;
import com.auri.entity.Porderentity;
import com.auri.service.IngredientsService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
	
	@Autowired
	IngredientsService ingreService;
	
	@PostMapping(path="/addIngredients")
	public ResponseEntity<Ingredients> addIngredients(@RequestBody Ingredients ingredients){
		return ingreService.addIngredients(ingredients);
	}
	
	@GetMapping(path = "/getAllIngredients")
	public ResponseEntity<List<Ingredients>> getAllIngredients(){
		return ingreService.getAllIngredients();
	}
	
	@DeleteMapping(path = "/deleteIngredients")
	public ResponseEntity<String> deleteIngredients(@RequestParam int id){
		return ingreService.deleteIngredients(id);
	}
	
	@GetMapping(path = "/getAllPO")
	public ResponseEntity<List<Porderentity>> getAllPO(){
		return ingreService.getAllPO();
	}
	
	@PostMapping(path = "/addPOR")
	public ResponseEntity<Porderentity>addPOR(@RequestBody Porderentity por){
		return ingreService.addPOR(por);
	}

	@DeleteMapping(path = "/deletePOR")
	public ResponseEntity<String> deletePOR(@RequestParam int id){
		return ingreService.deletePOR(id);
	}
	
	@GetMapping(path = "/getAllIngMappingBy")
	public ResponseEntity<List<IngredientMapping>> getIngMappingBy(@RequestParam int productID,int  varientID ){
		return ingreService.getIngMappingBy(productID,varientID);
	}
	@GetMapping(path ="/getAllIngMap")
	public ResponseEntity<List<IngredientMapping>> getAllMapping(){
		return ingreService.getAllMapping();
	}
	@PostMapping(path = "/mapIngAndPro")
	public ResponseEntity<List<IngredientMapping>>mapIngAndPro(@RequestBody List<IngredientMapping> mapIng){
		return ingreService.mapIngAndPro(mapIng);
	}
}
