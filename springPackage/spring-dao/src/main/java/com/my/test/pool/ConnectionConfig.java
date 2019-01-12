package com.my.test.pool;

public class ConnectionConfig {

	private String userName;
	private String password;
	private String url;
	private String driver;
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getDriver() {
		return driver;
	}
	public void setDriver(String driver) {
		this.driver = driver;
	}
	public ConnectionConfig(String userName, String password, String url, String driver) {
		super();
		this.userName = userName;
		this.password = password;
		this.url = url;
		this.driver = driver;
	}
	
}
