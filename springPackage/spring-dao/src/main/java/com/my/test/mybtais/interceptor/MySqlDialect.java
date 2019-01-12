package com.my.test.mybtais.interceptor;

public class MySqlDialect implements Dialect {

	@Override
	public String getPageSql(String originSql, int start, int end) throws Exception {
		return new StringBuilder(originSql).append(" limit ").append(start).append(" , ").append(end).toString();
	}

	@Override
	public String getCountString(String querySqlString) throws Exception {
		int limitIndex = querySqlString.lastIndexOf("limit");

		if(limitIndex != -1){
			querySqlString = querySqlString.substring(0, limitIndex != -1 ? limitIndex : querySqlString.length() - 1);
		}
		return "SELECT COUNT(*) FROM (" + querySqlString + ") tempTb";
	}

}
