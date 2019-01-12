package com.my.test.pool;

import java.sql.Connection;

import org.apache.commons.pool2.impl.GenericObjectPool;

public interface AbstractMyPoolConnectionFactory<T> {
	 GenericObjectPool<T> getPool();
}
