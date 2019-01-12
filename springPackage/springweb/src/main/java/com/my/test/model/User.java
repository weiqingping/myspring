package com.my.test.model;

import com.my.test.core.Body;
import com.my.test.core.MessageContent;

public class User extends Body{

	private String name;
	private int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
