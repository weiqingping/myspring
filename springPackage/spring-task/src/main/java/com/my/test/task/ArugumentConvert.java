package com.my.test.task;

public interface ArugumentConvert {
	public <T> Object  convert(String oringalStr, String targetKey, Class<T> targetClass) throws Exception ;

}
