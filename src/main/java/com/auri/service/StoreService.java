package com.auri.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.ProductRepo;
import com.auri.dao.ProductsInStoreRepo;
import com.auri.dao.StoreSeatRepo;
import com.auri.dao.StoreTableRepo;
import com.auri.entity.ProductTable;
import com.auri.entity.StoreProductTable;
import com.auri.entity.StoreSeatTable;
import com.auri.entity.StoreTable;
import com.auri.model.ApiResponse;
import com.auri.model.ProductsListTable;
import com.auri.model.StoreList;
import com.auri.model.ViewStoreAndProducts;

@Service
public class StoreService {

	@Autowired
	StoreTableRepo storeTable;

	@Autowired
	ProductsInStoreRepo inStoreProducts;

	@Autowired
	ProductRepo productTableRepo;

	@Autowired
	StoreSeatRepo storeSeatrepo;
	

	public ResponseEntity<StoreTable> signup(StoreTable signup) {
		return new ResponseEntity<StoreTable>(storeTable.save(signup),HttpStatus.OK);
	}
	public ResponseEntity<StoreTable> signin(StoreTable signin) {
		StoreTable st = storeTable.findByStoreIdAndPassword(signin.getStoreId(), signin.getPassword());
		if(st==null) {
			return new ResponseEntity<StoreTable>(st,HttpStatus.FORBIDDEN);
		}
		return new ResponseEntity<StoreTable>(st , HttpStatus.OK) ;
	}
	
	public ResponseEntity<StoreTable> addnewStore(StoreTable newstore) {

		System.out.println(newstore.getStoreId());
		StoreTable s = storeTable.save(newstore);
		System.out.println(s.getStoreId());
		
		return new ResponseEntity<StoreTable>(s,HttpStatus.OK);
	}

	public ResponseEntity<Iterable<StoreTable>> getAll() {
		return new ResponseEntity<Iterable<StoreTable>>(storeTable.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<ViewStoreAndProducts> addProducts(int storeID, int productID) {
		
		StoreProductTable product  = new StoreProductTable();
		StoreProductTable p = inStoreProducts.findByStoreIDAndProductID(storeID, productID);
		if(p!=null) {
			product = p;
		}else {
		product.setEnable(false);
		product.setOffeID(0);
		product.setProductID(productID);
		product.setStoreID(storeID);
		}
		StoreProductTable data = inStoreProducts.save(product);
		return viewStoredetails(storeID) ;
	}

	public ResponseEntity<ApiResponse> enableProduct(int storeID, int productID, boolean enable) {

		List<StoreProductTable> s = inStoreProducts.findByproductIdAndStoreID(productID, storeID);
		if (!s.isEmpty()) {

			StoreProductTable product = new StoreProductTable();

			product.setOffeID(s.get(0).getOffeID());
			product.setProductEnableID(s.get(0).getProductEnableID());
			product.setStoreID(s.get(0).getStoreID());
			product.setProductID(s.get(0).getProductID());
			product.setEnable(enable);

			inStoreProducts.save(product);

		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("500", "product not added"), HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "enable = " + enable), HttpStatus.OK);
	}

	public ResponseEntity<ViewStoreAndProducts> viewStoredetails(int storeID) {

		System.out.println("StoreID is" + storeID);

		ViewStoreAndProducts storeDetails = new ViewStoreAndProducts();
		StoreTable store = storeTable.findByStoreId(storeID);
		if (store != null) {

			storeDetails.setStoreID(store.getStoreId());
			storeDetails.setStoreLocation(store.getStoreAddress1() + "\n" + store.getStoreAddress2());
			storeDetails.setStoreName(store.getStoreName());
			storeDetails.setStoreType(store.getStoreType());
			storeDetails.setTaxInfo(store.getStoreTaxinfo());

			List<ProductsListTable> productsEnabled = new ArrayList<>();

			List<StoreProductTable> s = inStoreProducts.findAllByStoreID(storeID);
			System.out.println("Size is"+s.size());
			storeDetails.setNumberOfSellingProducts(s.size());	
			storeDetails.setStoreImage(store.getImageLink());

			for (StoreProductTable storeProductTable : s) {
				System.out.println(storeProductTable.getProductID());
				ProductsListTable pr = new ProductsListTable();
				ProductTable p = productTableRepo.findByItemCode(storeProductTable.getProductID());
				pr.setImage(p.getProductImage());
				pr.setProductID(p.getItemCode());
				pr.setProductName(p.getProductName());
				pr.setProductNature(p.getProductNature());
				pr.setProductPrice(p.getProductPrice());
				pr.setProductTax(p.getProductTax());
				productsEnabled.add(pr);
			}
			storeDetails.setProducts(productsEnabled);
		}
		return new ResponseEntity<ViewStoreAndProducts>(storeDetails, HttpStatus.OK);
	}

	public ResponseEntity<List<StoreList>> storeListView() {
		List<StoreList> listofStore = new ArrayList();
		Iterable<StoreTable> table = storeTable.findAll();
		for (StoreTable storeTable : table) {
			StoreList list = new StoreList();
			list.setStoreID(storeTable.getStoreId());
			list.setStoreLocation(storeTable.getStoreAddress1());
			list.setStoreName(storeTable.getStoreName());
			list.setStoreType(storeTable.getStoreType());
			list.setStoreImage(storeTable.getImageLink());
			listofStore.add(list);
		}
		return new ResponseEntity<List<StoreList>>(listofStore, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> deleteProductFromStore(int storeID, int productID) {

		List<StoreProductTable> items = inStoreProducts.findByproductIdAndStoreID(productID, storeID);
		for (StoreProductTable storeProductTable : items) {
			inStoreProducts.deleteByproductEnableID(storeProductTable.getProductEnableID());
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "success"), HttpStatus.OK);
	}

	public ResponseEntity<StoreSeatTable> addNewTable(StoreSeatTable newTable) {

		
		if(newTable.getTableId()>0) {
//			System.out.println("Updating progress");
			List<StoreSeatTable> check = storeSeatrepo.findAllByTableNameAndStoreId(newTable.getTableName(),newTable.getStoreId());
			if(check.size()>0) {
//				System.out.println("from if check " +newTable.getTableId());
				for (StoreSeatTable s : check) {
//					System.out.println(s.getTableId());
					if(s.getTableId() == newTable.getTableId() && s.getTableName().equals(newTable.getTableName())){
//						System.out.println(" check");
						
					}
				}
			}
		}
		return new ResponseEntity<StoreSeatTable>(storeSeatrepo.save(newTable),HttpStatus.OK);
	}
//					System.out.println("out check");
//				}
//				
//				try {
//					response.sendError(602, "table name is already exist");
//					return null;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}else {
//				
//				System.out.println(" updating");
//				storeSeatrepo.save(newTable);
//				return new ResponseEntity<StoreSeatTable>(HttpStatus.OK);
//			}
//			
//		}else {
//			List<StoreSeatTable> t = storeSeatrepo.findAllByTableNameAndStoreId(newTable.getTableName(),newTable.getStoreId());
//			if(t.size()>0) {
//				try {
//					response.sendError(602, "table name is already exist");
//					return null;
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}else {
//				System.out.println(" new entry");
//				storeSeatrepo.save(newTable);
//				return new ResponseEntity<ApiResponse>(new ApiResponse("200", "added as new "), HttpStatus.OK);
//				
//			}
//			
//		}
//		
//	
//		
//		System.out.println("Out of if");
//		return null;
//		
//
//		
//	}

	public ResponseEntity<ApiResponse> deleteTable(int tableId) {
		storeSeatrepo.deleteBytableId(tableId);
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "success"), HttpStatus.OK);
	}

	public ResponseEntity<Iterable<StoreSeatTable>> getAllStoreTable(int storeID) {
		Iterable<StoreSeatTable> list = storeSeatrepo.findAllByStoreId(storeID);
		return new ResponseEntity<Iterable<StoreSeatTable>>(list, HttpStatus.OK);
	}

	public ResponseEntity<StoreSeatTable> getStoreTable(int tableId) {
		// TODO Auto-generated method stub
		StoreSeatTable s = storeSeatrepo.findByTableId(tableId);
		return new ResponseEntity<StoreSeatTable>(s, HttpStatus.OK);
	}

	public ResponseEntity<StoreTable> editStoreDetails(int storeID) {
		StoreTable store = storeTable.findByStoreId(storeID);
		if (store != null) {
			return new ResponseEntity<StoreTable>(store, HttpStatus.OK);
		}
		return new ResponseEntity<StoreTable>(store, HttpStatus.NOT_FOUND);
	}

	public ResponseEntity<ApiResponse> deleteStore(int storeID) {
		
		int i = storeTable.deleteBytableId(storeID);
		System.out.println(i);
		List<StoreProductTable> storep = inStoreProducts.findAllByStoreID(storeID);

		for (StoreProductTable s : storep) {
			inStoreProducts.deleteByproductEnableID(s.getProductEnableID());
		}
		
		Iterable<StoreSeatTable> seat = storeSeatrepo.findAllByStoreId(storeID);
		for (StoreSeatTable s : seat) {
			storeSeatrepo.deleteBytableId(s.getTableId());
		}
		// TODO delete counter repo 
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "deleted"),HttpStatus.OK);
	}

	
	
	public static Date removeTime(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 12);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        System.out.println("date  == "+cal.getTime());
        
        
		String myFormat = "dd-MM-yyyy HH:mm:ss.SSS";
	    SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
	    String d = sdf.format(cal.getTime());
	    
	    try {
	    	date =sdf.parse(d);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println(d);
        return date;
    }

	public ResponseEntity<List<Integer>> getAllproductIds(int storeID) {
		List<Integer> productID = new ArrayList<>();
		List<StoreProductTable> storep = inStoreProducts.findAllByStoreID(storeID);
		for (StoreProductTable sp : storep) {
			productID.add(sp.getProductID());
		}
		
		return new ResponseEntity<List<Integer>>(productID,HttpStatus.OK);
	}
	

}
