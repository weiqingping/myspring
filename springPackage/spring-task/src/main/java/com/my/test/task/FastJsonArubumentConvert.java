package com.my.test.task;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
public  class FastJsonArubumentConvert implements ArugumentConvert {

	@Override
	public <T> Object  convert(String oringalStr, String targetKey, Class<T> targetClass) throws Exception {
		Object object=null;     
		        try {
		        	 object = JSON.parse(oringalStr);
				} catch (Exception e) {
					return oringalStr;
				}
		    	
				if (object instanceof JSONObject) {
					if(StringUtils.isEmpty(targetKey)){
						return JSON.parseObject(oringalStr, targetClass);
					}
					JSONObject jsonObject = (JSONObject) object;
					String value =jsonObject.getString(targetKey);
					
					return convert(value,null,targetClass); 
				} else if (object instanceof JSONArray) {
					JSONArray jsonArray = (JSONArray) object;
					return jsonArray.toArray(new Object[]{});
				} else {
					return oringalStr;
				}
			} 
			
		
	
	}


