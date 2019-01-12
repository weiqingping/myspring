package com.my.test.pool;

import java.sql.Connection;

import org.apache.commons.pool2.impl.DefaultPooledObjectInfo;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class MyPoolConnectionFactory implements AbstractMyPoolConnectionFactory {
	private    GenericObjectPool<Connection> totalPool;
	private static MyPoolConnectionFactory instance;

	public static MyPoolConnectionFactory instance(GenericObjectPoolConfig config,ConnectionConfig connConfig){
		synchronized (MyPoolConnectionFactory.class) {
			if(instance==null){
				
				GenericObjectPool<Connection> pool = new GenericObjectPool<>(
						new MyPooledObjectFactory(null, connConfig), config);
				instance=new MyPoolConnectionFactory(pool);
				instance.setTotalPool(pool);
			
			}
		}
		
		return instance;
		
	}
	

	public GenericObjectPool<Connection> getTotalPool() {
		return totalPool;
	}


	public void setTotalPool(GenericObjectPool<Connection> totalPool) {
		this.totalPool = totalPool;
	}


	private MyPoolConnectionFactory(GenericObjectPool<Connection> totalPool) {
     this.totalPool=totalPool;
	}



	@Override
	public GenericObjectPool getPool() {
		// TODO Auto-generated method stub
		return totalPool;
	}
	
	public void makeObject() throws Exception{
		totalPool.addObject();
	}

	public Connection borrowObject() throws Exception {
		return totalPool.borrowObject();
	}

	public void returnObject(Connection conn) throws Exception {
		totalPool.returnObject(conn);
	}

	public void close(Connection conn) throws Exception {
		conn.close();
	}

	public void clear() throws Exception {
		totalPool.clear();
	}
	
	public void init(int number) throws Exception{
		totalPool.addObject();
	}
	
	public void test(){
		totalPool.listAllObjects().stream().forEach((DefaultPooledObjectInfo info)->System.out.print("-------------"+info.toString()));
	}

}
