package com.my.test.pool;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.commons.dbcp.DriverManagerConnectionFactory;
import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPool;

public class MyPooledObjectFactory implements PooledObjectFactory<Connection> {

    private  ConnectionConfig connectionConfig;
    private  GenericObjectPool<Connection> pool;
    
	public MyPooledObjectFactory(GenericObjectPool<Connection> pool,ConnectionConfig connectionConfig) {
		super();
		this.pool=pool;
		this.connectionConfig=connectionConfig;
	}

	ThreadLocal<PooledObject<Connection>> connections=new ThreadLocal<PooledObject<Connection>>();
	@Override
	public PooledObject<Connection> makeObject() throws Exception {
		if(null==connections.get()){
			return new DefaultPooledObject<Connection>( new ProxyConectionFactory(pool,connectionConfig).getConnection());
		}else{
			return  connections.get();
		}
	}


	@Override
	public void destroyObject(PooledObject<Connection> p) throws Exception {
		p.getObject().close();
		
	}

	@Override
	public boolean validateObject(PooledObject<Connection> p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void passivateObject(PooledObject<Connection> p) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	
	


	   
   }




