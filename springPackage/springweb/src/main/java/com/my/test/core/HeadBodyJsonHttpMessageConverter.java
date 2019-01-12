package com.my.test.core;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


public class HeadBodyJsonHttpMessageConverter<T> extends FastJsonHttpMessageConverter {
	
	protected Object readChildInternal(HttpInputMessage inputMessage,Class<?>childClass,String key)
			throws IOException, HttpMessageNotReadableException {

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		InputStream in = inputMessage.getBody();

		byte[] buf = new byte[1024];
		for (;;) {
			int len = in.read(buf);
			if (len == -1) {
				break;
			}

			if (len > 0) {
				baos.write(buf, 0, len);
			}
		}

		byte[] bytes = baos.toByteArray();
		JSONObject  jsonObj=JSON.parseObject(bytes, 0, bytes.length, this.getCharset().newDecoder(), JSONObject.class);
		if(null!=jsonObj){
			return jsonObj.getObject(key, childClass);
		}
		return null;
	}

}
