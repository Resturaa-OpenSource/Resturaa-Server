package com.auri.model;

public class ProductReport {   
//	implements  Comparable<ProductReport>{
	private int sellingCount;
	private String productName;
	
	public ProductReport(int sellcount,String productName) {
		// TODO Auto-generated constructor stub
		this.productName = productName;
		this.sellingCount = sellcount;
	}
	public int getSellingCount() {
		return sellingCount;
	}
	public void setSellingCount(int sellingCount) {
		this.sellingCount = sellingCount;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
//	@Override
//	public int compareTo(ProductReport o) {
//		// TODO Auto-generated method stub
//		
//		 if (this.productName.equalsIgnoreCase( o.getProductName())) {
//			 System.out.println("compare   :"+o.getProductName());
////			 this.sellingCount =  this.sellingCount + o.getSellingCount();
//             return 0;
//         } else  {
//             return -1;
//         }
//	}

	
}
