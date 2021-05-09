package com.auri.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.IngredientMappingDao;
import com.auri.dao.IngredientsDao;
import com.auri.dao.PurchaseOrderDao;
import com.auri.entity.IngredientMapping;
import com.auri.entity.Ingredients;
import com.auri.entity.Porderentity;

@Service
public class IngredientsService {

	@Autowired
	IngredientsDao ingreDao;
	
	@Autowired
	PurchaseOrderDao purchaseDAO;
	
	@Autowired 
	IngredientMappingDao ingMappiningDao;
	
	public ResponseEntity<Ingredients> addIngredients(Ingredients ingre) {
		// TODO Auto-generated method stub
		return new ResponseEntity<Ingredients>(ingreDao.save(ingre),HttpStatus.OK);
	}

	public ResponseEntity<List<Ingredients>> getAllIngredients() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<Ingredients>>((List<Ingredients>) ingreDao.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<String> deleteIngredients(int id) {
		// TODO Auto-generated method stub
	    ingreDao.deleteById(id);
		return new ResponseEntity<String>("deleted",HttpStatus.OK);
	}

	public ResponseEntity<List<Porderentity>> getAllPO() {
		return new ResponseEntity<List<Porderentity>>((List<Porderentity>) purchaseDAO.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<Porderentity> addPOR(Porderentity por) {
		return new ResponseEntity<Porderentity>(purchaseDAO.save(por),HttpStatus.OK);
	}

	public ResponseEntity<String> deletePOR(int id) {
		purchaseDAO.deleteById(id);
		return new ResponseEntity<String>("Deleted",HttpStatus.OK);
	}

	public ResponseEntity<List<IngredientMapping>> getIngMappingBy(int productID, int varientID) {
		return new ResponseEntity<List<IngredientMapping>>(ingMappiningDao.findAllByProductIDAndProductVarientID(productID, varientID),HttpStatus.OK);
	}

	public ResponseEntity<List<IngredientMapping>> getAllMapping() {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<IngredientMapping>>((List<IngredientMapping>) ingMappiningDao.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<List<IngredientMapping>> mapIngAndPro(List<IngredientMapping> mapIng) {
		// TODO Auto-generated method stub
		return new ResponseEntity<List<IngredientMapping>>((List<IngredientMapping>) ingMappiningDao.saveAll(mapIng),HttpStatus.OK);	
	}
	

}
