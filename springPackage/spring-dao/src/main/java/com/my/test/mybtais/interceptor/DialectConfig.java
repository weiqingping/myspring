package com.my.test.mybtais.interceptor;

import java.util.HashMap;
import java.util.Map;

public class DialectConfig {

	private static Map<String, Dialect> dialectMap;
	static {
		dialectMap = new HashMap<String, Dialect>() {
			{
				put("mysql", new MySqlDialect());
				put("oracle", new OracleDialect());

			}

		};
	}
	public static Map<String, Dialect> getDialectMap() {
		return dialectMap;
	}
	
	
	
}
