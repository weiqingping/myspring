package com.my.test.api.model;

import java.io.Serializable;

public class Product implements Serializable {
	private int productId;
	private String productName;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public Product(int productId, String productName) {
		super();
		this.productId = productId;
		this.productName = productName;
	}

	public Product() {
		super();
	}
	
	public String toString() {
		StringBuilder sb=new StringBuilder();
		sb.append("[").append("productId="+productId).append(",").append("productName="+productName);
		return sb.toString();
	}

}
