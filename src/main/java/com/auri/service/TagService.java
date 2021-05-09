package com.auri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.ProductRepo;
import com.auri.dao.TagMapRepo;
import com.auri.dao.TagRepo;
import com.auri.entity.ProductTable;
import com.auri.entity.TagMappingTable;
import com.auri.entity.TagTable;
import com.auri.model.ApiResponse;

@Service
public class TagService {

	@Autowired
	TagRepo tagRepo;
	@Autowired
	TagMapRepo tagMapRepo;
	@Autowired
	ProductRepo productRepo;
	
	public ResponseEntity<TagTable> addNewTag(TagTable tag) {
		
		return new ResponseEntity<TagTable>(tagRepo.save(tag),HttpStatus.OK);
	}

	public ResponseEntity<Iterable<TagTable>> getAlltags() {
		return new ResponseEntity<Iterable<TagTable>>(tagRepo.findAll(),HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteTagID(int tagID) {
		tagRepo.deleteBytagID(tagID);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "deleted"),HttpStatus.OK);
	}

	public ResponseEntity<TagTable> getTagById(int tagID) {
		return new ResponseEntity<TagTable>(tagRepo.findByTagID(tagID),HttpStatus.OK);
	}

	public ResponseEntity<List<ProductTable>> getTaggedProduct(int tagID) {
		List<TagMappingTable> tags = tagMapRepo.findAllByTagID(tagID);
		List<ProductTable> pt = new ArrayList<ProductTable>();
		for (TagMappingTable tagMappingTable : tags) {
			ProductTable product = productRepo.findByItemCode(tagMappingTable.getProductID());
			pt.add(product);
			}
		
		
		
		return new ResponseEntity<List<ProductTable>>(pt,HttpStatus.OK);
	}

	public ResponseEntity<TagMappingTable> addTagProduct(int tagID, int productID) {
		TagMappingTable tag = new TagMappingTable();
		tag.setProductID(productID);
		tag.setTagID(tagID);
		return new ResponseEntity<TagMappingTable>(tagMapRepo.save(tag),HttpStatus.OK);
	}

	public ResponseEntity<List<Integer>> getAllproductIds(int tagID) {
		List<Integer> productID = new ArrayList<>();
		List<TagMappingTable> tagrep = tagMapRepo.findAllByTagID(tagID);
		for (TagMappingTable tm : tagrep) {
			productID.add(tm.getProductID());
		}
		
		return new ResponseEntity<List<Integer>>(productID,HttpStatus.OK);
	}

	public ResponseEntity<TagMappingTable> deleteById(int tagID, int productID) {
		TagMappingTable tg = tagMapRepo.findByTagIDAndProductID(tagID, productID);
		tagMapRepo.delete(tg);
		return new ResponseEntity<TagMappingTable>(HttpStatus.OK);
}
}
