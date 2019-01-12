package com.my.test.api.model;

import java.io.Serializable;

public class User implements Serializable{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	
	
	public User(int id, String name) {
		this.id = id;
		this.name = name;
	}


	public User() {
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
