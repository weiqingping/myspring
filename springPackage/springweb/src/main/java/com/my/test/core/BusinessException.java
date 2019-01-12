package com.my.test.core;

import java.io.Serializable;

public class BusinessException extends RuntimeException implements Serializable {

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	private int code;
	private String message;

	public BusinessException(int code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}

	public BusinessException() {

	}

	public BusinessException(String message, Throwable cause) {
		super(message, cause);

	}
	
	public BusinessException( Throwable cause) {
		super(cause);

	}

}
