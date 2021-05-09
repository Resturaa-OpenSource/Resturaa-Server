package com.auri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.auri.model.OrderCount;
import com.auri.service.OrderService;


@Controller
public class WebSocketController {

	@Autowired
	OrderService orderservice;
	

//	
	@MessageMapping("/hello/{id}")
	@SendTo("/topic/hi/{id}")
	public String greeting(String user, @DestinationVariable String id) throws Exception {
		System.out.println(id+" :"+user);
		return new String("Hi, " + user + "!");
	}
	
	@MessageMapping("/ordercount/{storeID}")
	@SendTo("/topic/ordercount/{storeID}")
	public OrderCount appMenu(int storeid, @DestinationVariable String storeID) {
		System.out.println(storeid);
		return orderservice.getCount(storeid);
	}
	@MessageMapping("/notify/{storeID}")
	@SendTo("/topic/notify/{storeID}")
	public String notify(String storeid, @DestinationVariable String storeID) {
		System.out.println(storeid);
		return storeid;
	}
	
	

}