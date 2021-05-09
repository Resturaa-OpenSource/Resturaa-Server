package com.auri.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.auri.entity.OrderTable;
import com.auri.entity.ProductTable;
import com.auri.entity.ReservationTable;
import com.auri.entity.StoreSeatTable;
import com.auri.entity.TagTable;
import com.auri.model.ApiResponse;
import com.auri.model.Invoice;
import com.auri.model.OrderCount;
import com.auri.model.ProductDetailsPos;
import com.auri.model.Reservation;
import com.auri.model.ReservationListView;
import com.auri.service.OrderService;
import com.razorpay.RazorpayException;

@RestController
@RequestMapping("/api/order")
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping(path = "/addOrder")
	public @ResponseBody ResponseEntity<OrderTable> addNewOrder(@RequestBody OrderTable neworder) {
		return orderService.addNewOrder(neworder);
	}
	@GetMapping(path = "/getCount")
	public @ResponseBody ResponseEntity<OrderCount> getCount(@RequestParam int storeID){
		return  new ResponseEntity<OrderCount>(orderService.getCount(storeID),HttpStatus.OK);
	}

	@GetMapping(path = "/getAll")
	public @ResponseBody ResponseEntity<Iterable<OrderTable>> getAllOrders() {
		return orderService.getallOrder();
	}

	@GetMapping(path = "/getOrderTableById")
	public @ResponseBody ResponseEntity<OrderTable> getOrderTableById(@RequestParam int orderID) {
		return orderService.getOrderTableById(orderID);
	}

	@GetMapping(path = "/getRunningOrder")
	public @ResponseBody ResponseEntity<Iterable<OrderTable>> getRunningOrder(@RequestParam int storeID,
			String status) {
		return orderService.getRunningOrder(storeID, status);
	}
	@GetMapping(path = "/history")
	public @ResponseBody ResponseEntity<Iterable<OrderTable>> getHistory(@RequestParam int custID){
		return orderService.getHistory(custID);
	}

	@GetMapping(path = "/getAvailableTables")
	public @ResponseBody ResponseEntity<List<StoreSeatTable>> getAvailableTables(@RequestParam int storeID, Date sDate,
			Date eDate) {
		return orderService.getAvailableTables(storeID, sDate, eDate);
	}

	@GetMapping(path = "/getRecentProducts")
	public @ResponseBody ResponseEntity<List<ProductDetailsPos>> getRecentProducts(@RequestParam int storeID) {
		return orderService.getRecentProducts(storeID);

	}

	@GetMapping(path = "/getTags")
	public @ResponseBody Iterable<TagTable> getAllTags() {
		return orderService.getTags();

	}

	@GetMapping(path = "/getTagProducts")
	public @ResponseBody ResponseEntity<List<ProductTable>> getTags(@RequestParam int storeID, int tagId) {
		return orderService.getTagProducts(storeID, tagId);

	}

	@PutMapping(path = "/closeOrder")
	public @ResponseBody ResponseEntity<ApiResponse> closeOrder(@RequestBody OrderTable neworder) {
		return orderService.updateOrder(neworder);

	}

	@PostMapping(path = "/newReservation")
	public @ResponseBody ResponseEntity<ApiResponse> addNewReservation(@RequestBody Reservation neworder) {
		return orderService.newReservation(neworder);
	}

	@GetMapping(path = "/viewReservation")
	public @ResponseBody ResponseEntity<List<ReservationListView>> viewReservation(@RequestParam int storeID) {
		return orderService.viewReservation(storeID);
	}

	@DeleteMapping(path = "/cancelReservation")
	public @ResponseBody ResponseEntity<ApiResponse> cancelReservation(@RequestParam int reservationID) {
		return orderService.cancelReservation(reservationID);

	}

	@GetMapping(path = "/getTableforReserve")
	public @ResponseBody ResponseEntity<List<StoreSeatTable>> getAllTableForReserve(@RequestParam Date reservationID,
			int storeID) {
		return orderService.getAllTableForReserve(reservationID, storeID);

	}

	@PostMapping(path = "/addReservation")
	public @ResponseBody ResponseEntity<ReservationTable> addReservationTable(@RequestBody ReservationTable table) {
		return orderService.addReservation(table);

	}

	@GetMapping(path = "/getAllReservation")
	public @ResponseBody ResponseEntity<List<ReservationTable>> getAllReservation(@RequestParam int storeID) {
		return orderService.getAllReservation(storeID);
	}

	@PostMapping(path = "/netbanking")
	public @ResponseBody ResponseEntity<List<String>> netBanking(@RequestBody Invoice invoice) {
		return orderService.netBanking(invoice);
	}

	@GetMapping(path = "/checkNetBankingStatus")
	public @ResponseBody ResponseEntity<ApiResponse> check(@RequestParam String ID) {
		try {
			return orderService.checkInvoiceStatus(ID);
		} catch (RazorpayException e) {

			e.printStackTrace();
			return new ResponseEntity<ApiResponse>(new ApiResponse("200", "200"), HttpStatus.BAD_REQUEST);
		}

	}

}
