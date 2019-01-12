package com.my.test.task;
public class DefaultScheduleArugumentResolver implements ScheduleArugumentResolver {

	private ArugumentConvert convert;


	public ArugumentConvert getConvert() {
		return convert;
	}

	public void setConvert(ArugumentConvert convert) {
		this.convert = convert;
	}

	public DefaultScheduleArugumentResolver(ArugumentConvert convert) {
		this.convert = convert;
	}

	public DefaultScheduleArugumentResolver() {
	}

	@Override
	public Object resolve(String oringalStr, String targetKey, Class<?> targetClass) throws Exception {
		// TODO Auto-generated method stub
		return convert.convert(oringalStr, targetKey, targetClass);
	}

	@Override
	public Object resolve(String oringalStr, Class<?> targetClass) throws Exception {
		// TODO Auto-generated method stub
		return resolve(oringalStr, null, targetClass);
	}



	





}
