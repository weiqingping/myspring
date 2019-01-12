package com.my.test.web;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import com.my.test.core.BusinessException;

@ControllerAdvice
public class CommonHelperException {
	@ExceptionHandler({ Exception.class })
	
	public @ResponseBody Map<String, String> dealHandlerExceptionResolver(Exception e) {
		Map<String,String>resultMap=new HashMap<String, String>();
		e.printStackTrace();
		if (e instanceof BusinessException) {
			resultMap.put("code", ((BusinessException)e).getCode()+"");
			resultMap.put("message", ((BusinessException)e).getMessage());
			return resultMap;
		} else {
			resultMap.put("code", "9999");
			resultMap.put("message",e.getMessage());
			return resultMap;
		}

	}
}
