package com.auri.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import com.auri.dao.CustomerRepo;
import com.auri.dao.OfferRepo;
import com.auri.dao.OrderRepo;
import com.auri.dao.ProductRepo;
import com.auri.dao.ProductVariantRepo;
import com.auri.dao.ProductsInStoreRepo;
import com.auri.dao.ReservationRepo;
import com.auri.dao.StoreSeatRepo;
import com.auri.dao.TagMapRepo;
import com.auri.dao.TagRepo;
import com.auri.entity.OfferTable;
import com.auri.entity.OrderTable;
import com.auri.entity.ProductTable;
import com.auri.entity.ProductVariants;
import com.auri.entity.ReservationTable;
import com.auri.entity.SelectedTable;
import com.auri.entity.StoreProductTable;
import com.auri.entity.StoreSeatTable;
import com.auri.entity.TagMappingTable;
import com.auri.entity.TagTable;
import com.auri.model.ApiResponse;
import com.auri.model.OrderCount;
import com.auri.model.ProductDetailsPos;
import com.auri.model.Reservation;
import com.auri.model.ReservationListView;
import com.razorpay.Invoice;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;

@Service
public class OrderService {

	@Autowired
	OrderRepo orderRepo;

	@Autowired
	StoreSeatRepo seatsRepo;

	@Autowired
	TagRepo tagRepo;
	@Autowired
	TagMapRepo tagMapRepo;
	@Autowired
	ProductsInStoreRepo inStoreProducts;
	@Autowired
	ProductRepo proRepo;

	@Autowired
	ReservationRepo reservationRepo;

	@Autowired
	CustomerRepo customerRepo;

	@Autowired
	ProductVariantRepo productVariantRepo;
	@Autowired
	OfferRepo offer;

	private SimpMessagingTemplate temp = null;

	@Autowired
	void WebSocketController(SimpMessagingTemplate s) {
		this.temp = s;
	}

	public ResponseEntity<OrderTable> addNewOrder(OrderTable neworder) {
//		System.out.println("Store Seat count is"+neworder.getTables().get(0).getSeatCount());		
		orderRepo.save(neworder);
		// send notify msg to client
		this.temp.convertAndSend("/topic/notify/" + neworder.getStoreID(), new String("neworder"));
		this.temp.convertAndSend("/topic/ordercount/"+neworder.getStoreID(),getCount(neworder.getStoreID()));
		return new ResponseEntity<OrderTable>(orderRepo.save(neworder), HttpStatus.OK);
	}

	public ResponseEntity<Iterable<OrderTable>> getallOrder() {
		return new ResponseEntity<Iterable<OrderTable>>(orderRepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<List<StoreSeatTable>> getAvailableTables(int storeID, Date dateStart, Date dateEnd) {

		List<ReservationTable> reserve = null;

		reserve = reservationRepo.findAllByStartTimeBetweenAndStoreId(getCurrentTimeStamp(dateStart),
				getCurrentTimeStamp(dateEnd), storeID);

		System.out.println("table reserve" + reserve.size());

		// fetch all table from store
		List<StoreSeatTable> table = seatsRepo.findAllByStoreId(storeID);
		System.out.println("table no " + table.size());
//		fetch all running order
		List<OrderTable> order = orderRepo.findAllByStoreIDAndStatus(storeID, "takeorder");
		System.out.println("oreder no " + order.size());

		for (ReservationTable r : reserve) {
			if (r.getOrderId() != 0) {
				OrderTable or = orderRepo.findByOrderNumber(r.getOrderId());
				if (or.getStatus() == "Reservation") {
					order.add(or);
				}
				System.out.println("Date  " + r.getStartTime());
			}

		}

		List<SelectedTable> selected = new ArrayList<>();
		for (OrderTable orederTable : order) {
			selected.addAll(orederTable.getTables());
		}
//		order.
		for (StoreSeatTable sst : table) {
			for (SelectedTable s : selected) {
				if (s.getTableID() == sst.getTableId()) {
					int a = s.getSeatCount();
					sst.setSeatOccupied(a);
					System.out.println("int " + a + " ");
					if (sst.getSeatOccupied() == 0) {
						table.remove(sst);
					}
				}
			}
		}

		return new ResponseEntity<List<StoreSeatTable>>(table, HttpStatus.OK);
	}

	public ResponseEntity<Iterable<OrderTable>> getRunningOrder(int storeID, String Status) {

		System.out.println(storeID + "    " + Status);
		Iterable<OrderTable> t = orderRepo.findAllByStoreIDAndStatus(storeID, Status);
		return new ResponseEntity<Iterable<OrderTable>>(t, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductDetailsPos>> getRecentProducts(int storeID) {
		List<ProductDetailsPos> prolist = new ArrayList<>();
//		List<StoreProductTable> proMaps = inStoreProducts.findAllBystoreIdAndEnabled(storeID, true);
//		List<Integer> i = new ArrayList<>();
//		for (StoreProductTable storeProductTable : proMaps) {
//			i.add(storeProductTable.getProductID());
//		}
//		List<ProductTable> products = proRepo.findAllByItemCode(i);
//		for (ProductTable p : products) {
//			
//			List<ProductVariants> var = productVariantRepo.findAllByProductID(p.getItemCode());
//			List<TagMappingTable> tags = tagMapRepo.findAllByProductID(p.getItemCode());
//			// adding to new variable;
//			pro.setProduct(p);
//			pro.setProductVariants(var);
//			pro.setTagIds(tags);
//			prolist.add(pro);
//		}
//		

		/// new logic
		List<StoreProductTable> storeproID = inStoreProducts.findAllBystoreIdAndEnabled(storeID, true);
		for (StoreProductTable spid : storeproID) {

			ProductTable pro = proRepo.findByItemCode(spid.getProductID());
			List<ProductVariants> var = productVariantRepo.findAllByProductID(spid.getProductID());
			List<TagMappingTable> tags = tagMapRepo.findAllByProductID(spid.getProductID());
			OfferTable off = offer.findByOfferIdAndOfferEnable(spid.getOffeID(), true);

			ProductDetailsPos PDP = new ProductDetailsPos();

			PDP.setOffer(off);
			PDP.setProduct(pro);
			PDP.setTagIds(tags);
			PDP.setProductVariants(var);
			prolist.add(PDP);
		}

		System.out.println("Size of available products  :" + prolist.size());

		return new ResponseEntity<List<ProductDetailsPos>>(prolist, HttpStatus.OK);
	}

	public Iterable<TagTable> getTags() {

		return tagRepo.findAll();
	}

	public ResponseEntity<List<ProductTable>> getTagProducts(int storeID, int tagId) {
		// find all products by tag
		List<ProductTable> products = new ArrayList<>();
		List<Integer> i = new ArrayList<>();
		List<TagMappingTable> tagmaps = tagMapRepo.findAllByTagID(tagId);
		for (TagMappingTable tagMappingTable : tagmaps) {
			// 2,3
			i.add(tagMappingTable.getProductID());
		}

		List<StoreProductTable> proMaps = inStoreProducts.findAllBystoreIdAndEnabled(storeID, true);
		for (StoreProductTable storeProductTable : proMaps) {
//			3,5,6
			if (i.contains(storeProductTable.getProductID())) {
				products.add(proRepo.findByItemCode(storeProductTable.getProductID()));
			}
		}
		return new ResponseEntity<List<ProductTable>>(products, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> updateOrder(OrderTable neworder) {
		// fetch ordertable
		if (neworder.getOrderNumber() > 0) {
			orderRepo.save(neworder);
			this.temp.convertAndSend("/topic/notify/" + neworder.getStoreID(), new String("neworder"));

			return new ResponseEntity<ApiResponse>(new ApiResponse("200", "updated"), HttpStatus.OK);
		}

		// update
		return new ResponseEntity<ApiResponse>(new ApiResponse("400", "not updated"), HttpStatus.NOT_MODIFIED);
	}

	public ResponseEntity<ApiResponse> newReservation(Reservation neworder) {
		// add neworder to orderTable
		OrderTable data = orderRepo.save(neworder.getOrderTable());
		// create row on reservation table.
		ReservationTable reservation = new ReservationTable();
		reservation.setEndTime(neworder.getResDetails().getEndTime());
		reservation.setStartTime(neworder.getResDetails().getStartTime());
		reservation.setOrderId(data.getOrderNumber());
		reservation.setStoreId(neworder.getOrderTable().getStoreID());
		reservationRepo.save(reservation);
		this.temp.convertAndSend("/topic/notify/" + neworder.getOrderTable().getStoreID(), new String("reservation"));

		return new ResponseEntity<>(new ApiResponse("200", "success"), HttpStatus.OK);
	}

	public ResponseEntity<List<ReservationListView>> viewReservation(int storeID) {
		List<ReservationListView> reservList = new ArrayList<>();
		// fetch reservation table
		List<ReservationTable> reserve = reservationRepo.findAllByStoreId(storeID);
		if (reserve != null) {
			for (ReservationTable reservationTable : reserve) {
				ReservationListView r = new ReservationListView();
				OrderTable o = orderRepo.findByOrderNumber(reservationTable.getOrderId());
				r.setOrderTable(o);
				r.setReservationTable(reservationTable);
				reservList.add(r);
			}
		}
		this.temp.convertAndSend("/topic/notify/" + storeID, new String("reservation"));

		return new ResponseEntity<List<ReservationListView>>(reservList, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> cancelReservation(int reservationID) {

		// fetch reservation table
		ReservationTable r = reservationRepo.findByOrderId(reservationID);
		// fetch ordertable tables
		System.out.println(r.getOrderId() + "   reservavtion");
		OrderTable o = orderRepo.findByOrderNumber(r.getOrderId());
		// delete ordertable
		orderRepo.delete(o);
		// delete reservation table
		reservationRepo.delete(r);
		this.temp.convertAndSend("/topic/notify/" + r.getStoreId(), new String("reservation"));

		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "deleted"), HttpStatus.OK);
	}

	public ResponseEntity<List<StoreSeatTable>> getAllTableForReserve(Date date, int storeID) {

		// fetch reservation table

		// fetch all reservation on given date
		List<ReservationTable> reserveList = reservationRepo.findAllByStartTimeBetweenAndStoreId(endTme(date, -1),
				endTme(date, 1), storeID);
		System.out.println(reserveList.size());
		if (reserveList.size() > 0) {

		}
		// fetch orderTable
		// fetch table and remove from all tablelist;
		return null;
	}

	public Date endTme(Date date, int hours) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR_OF_DAY, date.getHours() + hours);
		System.out.println(calendar.getTime());
		return getCurrentTimeStamp(calendar.getTime());
	}

	public ResponseEntity<ReservationTable> addReservation(ReservationTable table) {
		this.temp.convertAndSend("/topic/notify/" + table.getStoreId(), new String("reservation"));

		return new ResponseEntity<ReservationTable>(reservationRepo.save(table), HttpStatus.OK);
	}

	public static Date getCurrentTimeStamp(Date date) {
		SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// dd/MM/yyyy

		String strDate = sdfDate.format(date);
		try {
			date = sdfDate.parse(strDate);
			System.out.println(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	public ResponseEntity<List<ReservationTable>> getAllReservation(int storeID) {

		List<ReservationTable> list = reservationRepo.findAllByStoreIdAndStartTimeAfter(storeID, new Date());
		System.out.println("Sizr " + list.size());
		return new ResponseEntity<List<ReservationTable>>(list, HttpStatus.OK);
	}

	public ResponseEntity<OrderTable> getOrderTableById(int orderID) {
		// TODO Auto-generated method stub
		return new ResponseEntity<OrderTable>(orderRepo.findByOrderNumber(orderID), HttpStatus.OK);
	}

	public ResponseEntity<List<String>> netBanking(com.auri.model.Invoice invoice) {

		JSONObject cust = new JSONObject();

		cust.put("name", invoice.getName());
		cust.put("contact", invoice.getPhoneNumber());

		JSONObject request = new JSONObject();
		request.put("amount", invoice.getAmount() * 100);
		request.put("sms_notify", "1");

		request.put("customer", cust);
		request.put("type", "link");
		request.put("view_less", 1);
		request.put("currency", "INR");
		request.put("description", "spice pos");
		String s = "no value";
		List<String> slist = new ArrayList<>();
		try {
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_09KlkNFVQzgD2M", "thSyVaUDMnmMPJ1b0zma3sV0");
			Invoice res = razorpayClient.Invoices.create(request);
//			System.out.println("******************");
//			System.out.println("" + res.get("short_url"));
//			System.out.println(res);
			s = res.get("short_url");
			slist.add(s);
			slist.add(res.get("id"));
//			Invoice invoice = razorpayClient.Invoices.fetch();
//			System.out.println(invoice);

		} catch (RazorpayException e) {
			e.printStackTrace();
			return new ResponseEntity<List<String>>(slist, HttpStatus.BAD_REQUEST);

		}
		return new ResponseEntity<List<String>>(slist, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> checkInvoiceStatus(String id) throws RazorpayException {
//		status.
		RazorpayClient razorpayClient = new RazorpayClient("rzp_test_09KlkNFVQzgD2M", "thSyVaUDMnmMPJ1b0zma3sV0");
		Invoice invoice = razorpayClient.Invoices.fetch(id);
		System.out.println(invoice);
		String s = invoice.get("status");

		System.out.println(s);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", s), HttpStatus.OK);
	}

	public OrderCount getCount(int storeID) {

		OrderCount count = new OrderCount();
		List<OrderTable> order = orderRepo.findAllByStoreIDAndStatus(storeID, "KOT");
		count.setNewOrder(order.size());
		List<ReservationTable> list = reservationRepo.findAllByStoreIdAndStartTimeAfter(storeID, new Date());
		count.setReservation(list.size());

		return count;
	}

	public ResponseEntity<Iterable<OrderTable>> getHistory(int custID) {

		return new ResponseEntity<Iterable<OrderTable>>(orderRepo.findAllByCustomerId(custID), HttpStatus.OK);
	}
}
