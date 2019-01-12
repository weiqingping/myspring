package com.my.test.core;

import java.io.Serializable;

public class MessageContent implements Serializable{
	private Header header;
	private Body body;
	public Header getHeader() {
		return header;
	}
	public void setHeader(Header header) {
		this.header = header;
	}
	public Body getBody() {
		return body;
	}
	public void setBody(Body body) {
		this.body = body;
	}

}
