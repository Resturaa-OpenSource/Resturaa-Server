package com.auri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.ProductTable;
import com.auri.entity.TagMappingTable;
import com.auri.entity.TagTable;
import com.auri.model.ApiResponse;
import com.auri.service.TagService;

@RestController
@RequestMapping("/api/Tag")
public class TagManagementController {

	@Autowired
	private TagService tagService;

	@PostMapping(path = "/add")
	public @ResponseBody ResponseEntity<TagTable> addNewTag(@RequestBody TagTable tag) {
		return tagService.addNewTag(tag);
	}
	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<TagTable>> getAlltags() {
		return tagService.getAlltags();
	}
	@GetMapping(path = "/getByTagId")
	public @ResponseBody ResponseEntity<TagTable> getTagById(@RequestParam  int tagID) {
		return tagService.getTagById(tagID);
	}
	
	@DeleteMapping(path = "/deleteTag")
	public @ResponseBody ResponseEntity<ApiResponse> deleteTag(@RequestParam  int tagID) {
		return tagService.deleteTagID(tagID);
	}
	
	@GetMapping(path = "/getTaggedProduct")
	public @ResponseBody ResponseEntity<List<ProductTable>> getTaggedProduct(@RequestParam int tagID) {
		return tagService.getTaggedProduct(tagID);
	}
	
	@GetMapping(path = "/addTagProduct")
	public @ResponseBody ResponseEntity<TagMappingTable> addTagProduct(@RequestParam int tagID, int productID) {
		return tagService.addTagProduct(tagID, productID);
	}
	
	@GetMapping(path ="/getAllProductByTagId")
	public @ResponseBody ResponseEntity<List<Integer>> getAllProductByTagId(@RequestParam int tagID){
		return tagService.getAllproductIds(tagID);
	}
	@DeleteMapping(path = "/deleteBytpId")
	public @ResponseBody ResponseEntity<TagMappingTable> deleteBytpId(@RequestParam int tagID, int productID){
		return tagService.deleteById(tagID,productID);
	}
}
