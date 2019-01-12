package com.my.test.dao.entity;

import java.io.Serializable;
import java.util.List;

public  class Page implements Serializable {
	private int pageSize;
	private int pageNumber;
	private int total;
	private List<?> result;
	
	public List<?> getResult() {
		return result;
	}
	public void setResult(List<?> result) {
		this.result = result;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	

}
