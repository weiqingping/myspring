package com.my.test.task;

public interface ScheduleArugumentResolver {
	public Object resolve(String oringalStr,String targetKey,Class<?>targetClass) throws Exception;
	public Object resolve(String oringalStr,Class<?>targetClass) throws Exception;


	public ArugumentConvert getConvert();

	public void setConvert(ArugumentConvert convert);

}
