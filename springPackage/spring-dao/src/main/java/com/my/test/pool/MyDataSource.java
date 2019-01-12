package com.my.test.pool;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;


public class MyDataSource extends AbstractDataSource {

	private String username;
	private String password;
	private String driverClassName;
	private String url;
	private int initialSize;
	private int minIdle;
	private int maxActive;
	private long maxWait;
	private GenericObjectPoolConfig config;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDriverClassName() {
		return driverClassName;
	}

	public void setDriverClassName(String driverClassName) {
		this.driverClassName = driverClassName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getInitialSize() {
		return initialSize;
	}

	public void setInitialSize(int initialSize) {
		this.initialSize = initialSize;
	}

	public int getMinIdle() {
		return minIdle;
	}

	public void setMinIdle(int minIdle) {
		this.minIdle = minIdle;
	}

	public int getMaxActive() {
		return maxActive;
	}

	public void setMaxActive(int maxActive) {
		this.maxActive = maxActive;
	}

	public long getMaxWait() {
		return maxWait;
	}

	public void setMaxWait(long maxWait) {
		this.maxWait = maxWait;
	}

	public void init() throws Exception {
		initConfig();
		ConnectionConfig connectionConfig = new ConnectionConfig(username, password, url, driverClassName);
		MyPoolConnectionFactory fact1 =MyPoolConnectionFactory.instance(config, connectionConfig);
		

		for (int j = 0; j < initialSize; j++) {
			fact1.makeObject();
		}
		
		fact1.test();

	}

	public void close() {
		ConnectionConfig connectionConfig = new ConnectionConfig(username, password, url, driverClassName);
		try {
			MyPoolConnectionFactory fact1 =MyPoolConnectionFactory.instance(config, connectionConfig);
			fact1.clear();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}
	}

	@Override
	public Connection getConnection() throws SQLException {
		// TODO Auto-generated method stub

		ConnectionConfig connectionConfig = new ConnectionConfig(username, password, url, driverClassName);
		try {
			MyPoolConnectionFactory fact1 = MyPoolConnectionFactory.instance(config, connectionConfig);

			return fact1.borrowObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	public void initConfig() throws Exception {
		// TODO Auto-generated method stub
		config = new GenericObjectPoolConfig();
		config.setMinIdle(minIdle);
		config.setMaxTotal(maxActive);
		config.setMaxWaitMillis(maxWait);
	}

}
