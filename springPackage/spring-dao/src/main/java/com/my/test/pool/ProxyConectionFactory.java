package com.my.test.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.pool2.impl.GenericObjectPool;

class ProxyConectionFactory implements InvocationHandler {
	private Connection target;
	private GenericObjectPool<Connection> pool;
	private  ConnectionConfig connConfig;
	
	public ProxyConectionFactory(GenericObjectPool<Connection> pool,ConnectionConfig connConfig){
		this.connConfig=connConfig;
	}

	public Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(connConfig.getDriver());
		Connection conn=DriverManager.getConnection(connConfig.getUrl(), connConfig.getUserName(), connConfig.getPassword());
		this.target=conn;
		return (Connection) Proxy.newProxyInstance(MyPooledObjectFactory.class.getClassLoader(),
				new Class[] { Connection.class }, this);
	}
     
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		if ("close".equals(method.getName())) {
			pool.returnObject((Connection) proxy);
		}else{
			return  method.invoke(target, args);
		}
		return null;
	}

}
