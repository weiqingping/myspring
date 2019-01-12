package com.my.test.mybtais.interceptor;

public interface Dialect {
	public static enum Type {
		MYSQL, ORACLE, SQLSERVER
	}
   public String getPageSql(String originSql,int start,int end)throws Exception;
   public String getCountString(String querySqlString)throws Exception;
}
