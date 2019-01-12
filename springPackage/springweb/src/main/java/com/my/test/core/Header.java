package com.my.test.core;

import java.io.Serializable;

public class Header implements Serializable {
	private String bizCode;
	private String methodCode;
	public String getBizCode() {
		return bizCode;
	}
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}
	public String getMethodCode() {
		return methodCode;
	}
	public void setMethodCode(String methodCode) {
		this.methodCode = methodCode;
	}
	

}
