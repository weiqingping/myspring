package com.my.test.dao.interceptor;

import java.util.Map;

import com.my.test.task.DataContainer;


public class TimeValidInterceptor implements Intercepotor{

	@Override
	public boolean test(DataContainer o) {
		long now = System.currentTimeMillis();
		if (null != o.getEfffectDate()) {
			if (o.getEfffectDate().getTime() > now) {
				return false;
			}
		}
		if (null != o.getExpireDate()) {
			if (o.getExpireDate().getTime() < now) {
				return false;
			}
		}
		return true;
	}



}
