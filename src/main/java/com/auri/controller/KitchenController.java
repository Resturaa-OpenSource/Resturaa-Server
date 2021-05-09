package com.auri.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.auri.entity.OrderTable;
import com.auri.service.KitchenService;

@RestController
@RequestMapping("/api/kitchen")
public class KitchenController {
	
	@Autowired
	KitchenService kitchenService; 
	

	private SimpMessagingTemplate temp = null;
	@Autowired
	void WebSocketController(SimpMessagingTemplate s){
		this.temp = s;
	}
	
	@GetMapping (path = "/runningorder")
	public @ResponseBody ResponseEntity<Iterable<OrderTable>> getAllOrder(@RequestParam int storeID){
//		this.temp.convertAndSend("/topic/notify/"+StoreID, new String("kitchen"));

		return kitchenService.getAllOrder(storeID);
	}
	
	
	@PutMapping(path = "/delivery")
	public @ResponseBody ResponseEntity<OrderTable> updateProductStatus(@RequestBody OrderTable order ){
		this.temp.convertAndSend("/topic/notify/"+order.getStoreID(), new String("kitchen"));
		this.temp.convertAndSend("/topic/notify/"+order.getStoreID(), new String("neworder"));
		return kitchenService.updateProductStatus(order);
	}
	
	
}
