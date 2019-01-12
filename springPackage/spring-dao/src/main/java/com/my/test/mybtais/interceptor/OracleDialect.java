package com.my.test.mybtais.interceptor;

public class OracleDialect implements Dialect {

	@Override
	public String getPageSql(String originSql, int start, int limit) throws Exception {
		return new StringBuilder(originSql).append(" limit ").append(start).append(" , ").append(limit).toString();
	}

	@Override
	public String getCountString(String querySqlString) throws Exception {
		int limitIndex = querySqlString.lastIndexOf("limit");

		if(limitIndex != -1){
			querySqlString = querySqlString.substring(0, limitIndex != -1 ? limitIndex : querySqlString.length() - 1);
		}
		return "SELECT COUNT(*) FROM (" + querySqlString + ") ";
	}

}
