package com.auri.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.auri.dao.OfferRepo;
import com.auri.dao.ProductRepo;
import com.auri.dao.ProductVariantRepo;
import com.auri.dao.ProductsInStoreRepo;
import com.auri.dao.TagMapRepo;
import com.auri.dao.TagRepo;
import com.auri.entity.OfferTable;
import com.auri.entity.ProductTable;
import com.auri.entity.ProductVariants;
import com.auri.entity.StoreProductTable;
import com.auri.entity.TagMappingTable;
import com.auri.entity.TagTable;
import com.auri.model.ApiResponse;
import com.auri.model.EditProducts;
import com.auri.model.ProductView;
import com.auri.model.ProductsListTable;
import com.google.gson.Gson;

@Service
public class ProductService {

	@Autowired
	ProductRepo productrepo;
	@Autowired
	ProductsInStoreRepo storeProducts;
	@Autowired
	OfferRepo offerRepo;
	@Autowired
	ProductVariantRepo variant;
	@Autowired
	TagMapRepo tagMapRepo;
	@Autowired
	TagRepo tagRepo;

	public ResponseEntity<Iterable<ProductTable>> getAllProducts() {

		return new ResponseEntity<Iterable<ProductTable>>(productrepo.findAll(), HttpStatus.OK);
	}

	public ResponseEntity<ProductTable> addNewProduct(EditProducts newProduct) {

		ProductTable product = productrepo.save(newProduct.getProduct());
		List<ProductVariants> vlist = new ArrayList<>();
		vlist = newProduct.getProductVariants();

		for (ProductVariants v : vlist) {
			v.setProductID(product.getItemCode());
			variant.save(v);
		}

		return new ResponseEntity<ProductTable>(product, HttpStatus.OK);
	}

	public ResponseEntity<List<ProductsListTable>> getAllStoreProducts(int storeID) {
		List<StoreProductTable> s = storeProducts.findAllByStoreID(storeID);
		List<ProductsListTable> list = new ArrayList<>();

		for (StoreProductTable storeProductTable : s) {
			ProductsListTable p = new ProductsListTable();
			ProductTable product = new ProductTable();
			OfferTable offer = new OfferTable();

			offer = offerRepo.findByOfferId(storeProductTable.getOffeID());
			if (offer != null) {
				p.setOfferName(offer.getOfferTittle());
			}

			product = productrepo.findByItemCode(storeProductTable.getProductID());
			if (product != null) {
				p.setImage(product.getProductImage());
				p.setEnable(storeProductTable.isEnable());
				p.setProductPrice(product.getProductPrice());
				p.setProductID(product.getItemCode());
				p.setProductName(product.getProductName());
				p.setProductNature(product.getProductNature());
				list.add(p);
			}

		}

		return new ResponseEntity<List<ProductsListTable>>(list, HttpStatus.OK);
	}

	public ResponseEntity<ProductTable> updateProduct(EditProducts product) {
		
		ProductTable p = new ProductTable();
		
		String gso =new Gson().toJson(product,EditProducts.class);
		System.out.println(gso);
		
		if (product.getProduct().getItemCode() != 0) {
			p = productrepo.findByItemCode(product.getProduct().getItemCode());
		}
		p.setItemcodePrefix(product.getProduct().getItemcodePrefix());
		p.setPrductRating(product.getProduct().getPrductRating());
		p.setProductDesc(product.getProduct().getProductDesc());
		p.setProductImage(product.getProduct().getProductImage());
		p.setProductName(product.getProduct().getProductName());
		p.setProductNature(product.getProduct().getProductNature());
		p.setProductPrice(product.getProduct().getProductPrice());
		p.setProductTax(product.getProduct().getProductTax());
		productrepo.save(p);

		List<ProductVariants> vlist = new ArrayList<>();
		vlist = product.getProductVariants();

		for (ProductVariants v : vlist) {
//			if (v.getProductVariantID() > 0) {
			variant.save(v);
//			}
		}

		return new ResponseEntity<ProductTable>(p, HttpStatus.OK);
	}

	public ResponseEntity<ProductView> viewProduct(int storeID, int productID) {
		ProductView viewproduct = new ProductView();
		StoreProductTable p = storeProducts.findByStoreIDAndProductID(storeID, productID);
		if (p !=null) {
			ProductTable item = productrepo.findByItemCode(productID);
			viewproduct.setProductID(productID);
			viewproduct.setProductDetails(item.getProductDesc());
			viewproduct.setImage(item.getProductImage());
			viewproduct.setProductName(item.getProductName());
			viewproduct.setProductNature(item.getProductNature());
			viewproduct.setPrefix(item.getItemcodePrefix());
			viewproduct.setEnable(p.isEnable());
			viewproduct.setProductPrice((int) item.getProductPrice());
			OfferTable offer = offerRepo.findByOfferId(p.getOffeID());

			if (offer != null) {

				viewproduct.setOfferDetails(offer.getOfferDetails());
				viewproduct.setOfferTittle(offer.getOfferTittle());
				viewproduct.setOfferValue((int) offer.getOfferValue());

			}
			List<ProductVariants> v = variant.findAllByProductID(productID);
			if (v != null) {
				viewproduct.setVariants(v);
			}
			List<TagMappingTable> tagmap = tagMapRepo.findAllByProductID(productID);
			if (tagmap != null) {
				System.out.println("Tags size " + tagmap.size());
				List<TagTable> tags = new ArrayList<>();
				for (TagMappingTable tagMappingTable : tagmap) {

					System.out.println("Tags id " + tagMappingTable.getTagID());

					TagTable t = tagRepo.findByTagID(tagMappingTable.getTagID());
					if (t != null) {
						tags.add(t);
						System.out.println("Addeedde");
					}
				}

				viewproduct.setTags(tags);
			}

		}

		return new ResponseEntity<ProductView>(viewproduct, HttpStatus.OK);
	}

	public ResponseEntity<EditProducts> editProduct(int productID) {
		EditProducts p = new EditProducts();
		p.setProduct(productrepo.findByItemCode(productID));
		p.setProductVariants(variant.findAllByProductID(productID));

		return new ResponseEntity<EditProducts>(p, HttpStatus.OK);
	}

	public ResponseEntity<StoreProductTable> enableProduct(int storeID, int productID, boolean enable) {
		List<StoreProductTable> s = storeProducts.findByproductIdAndStoreID(productID, storeID);
		StoreProductTable en;
		if (s.size() > 0) {
			s.get(0).setEnable(enable);
			en = storeProducts.save(s.get(0));

		} else {
			StoreProductTable t = new StoreProductTable();
			t.setEnable(enable);
			t.setProductID(productID);
			t.setStoreID(storeID);
			en = storeProducts.save(t);
		}
		return new ResponseEntity<StoreProductTable>(en, HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> applyOffer(int storeID, int productID, int offerId) {
		List<StoreProductTable> s = storeProducts.findByproductIdAndStoreID(productID, storeID);
		if (s.size() > 0) {
			s.get(0).setOffeID(offerId);
			storeProducts.save(s.get(0));

		} else {

			StoreProductTable t = new StoreProductTable();
			t.setOffeID(offerId);
			t.setProductID(productID);
			t.setStoreID(storeID);
			storeProducts.save(t);
		}
		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "updated"), HttpStatus.OK);
	}

	public ResponseEntity<ApiResponse> delete(int productID) {
		ProductTable pro = productrepo.findByItemCode(productID);
		if (pro != null) {

			List<TagMappingTable> tag = tagMapRepo.findAllByProductID(productID);
			if (tag != null) {
				tagMapRepo.deleteAll(tag);
			}

			List<StoreProductTable> store = storeProducts.findAllByProductID(productID);
			if (store != null) {
				storeProducts.deleteAll(store);
			}
			List<ProductVariants> v = variant.findAllByProductID(productID);
			if (v != null) {
				variant.deleteAll(v);
			}
			productrepo.delete(pro);

		} else {
			return new ResponseEntity<ApiResponse>(new ApiResponse("500", "product not exist"), HttpStatus.NOT_FOUND);
		}

		return new ResponseEntity<ApiResponse>(new ApiResponse("200", "deleted"), HttpStatus.OK);
	}

	public ResponseEntity<ProductVariants> deleteByPVId(int productVariantID) {
		ProductVariants pv = variant.findByProductVariantID(productVariantID);
		variant.delete(pv);
		return new ResponseEntity<ProductVariants>(pv, HttpStatus.OK);
	}
}
