package com.my.test.model;

import com.my.test.core.Body;

public class Inventory extends Body {
	private int inventoryId;
	private String inventoryName;

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public String getInventoryName() {
		return inventoryName;
	}

	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}

	public Inventory(int inventoryId, String inventoryName) {
		
		this.inventoryId = inventoryId;
		this.inventoryName = inventoryName;
	}

	public Inventory() {
		super();
	}
	
	

}
