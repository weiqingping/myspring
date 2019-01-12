package com.my.test.core;

import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;
import java.lang.reflect.Type;
import java.util.List;

import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.core.ResolvableType;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.InvalidMediaTypeException;
import org.springframework.http.MediaType;
import org.springframework.http.converter.GenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.method.annotation.RequestResponseBodyMethodProcessor;

import com.alibaba.fastjson.JSONObject;

public class HeaderBodyMethodProcessor extends RequestResponseBodyMethodProcessor {

	
	public HeaderBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters) {
		super(messageConverters);
	}
	public HeaderBodyMethodProcessor(List<HttpMessageConverter<?>> messageConverters,
			ContentNegotiationManager contentNegotiationManager){
		super(messageConverters, contentNegotiationManager);
		
	}
	
	
    

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return Body.class.isAssignableFrom(parameter.getParameterType());
		
	}

	protected <T> Object readWithMessageConverters(HttpInputMessage inputMessage,
			MethodParameter methodParam, Type targetType) throws IOException, HttpMediaTypeNotSupportedException {

		MediaType contentType;
		try {
			contentType = inputMessage.getHeaders().getContentType();
		}
		catch (InvalidMediaTypeException ex) {
			throw new HttpMediaTypeNotSupportedException(ex.getMessage());
		}
		if (contentType == null) {
			contentType = MediaType.APPLICATION_OCTET_STREAM;
		}

		Class<?> contextClass = methodParam.getContainingClass();

		for (HttpMessageConverter<?> converter : this.messageConverters) {
			if (converter instanceof GenericHttpMessageConverter) {
				GenericHttpMessageConverter<?> genericConverter = (GenericHttpMessageConverter<?>) converter;
				if (genericConverter.canRead(targetType, contextClass, contentType)) {
					if (logger.isDebugEnabled()) {
						logger.debug("Reading [" + targetType + "] as \"" +
								contentType + "\" using [" + converter + "]");
					}
					return genericConverter.read(targetType, contextClass, inputMessage);
				}
			}
			Class<T> targetClass = (Class<T>)
					ResolvableType.forMethodParameter(methodParam, targetType).resolve(Object.class);
			if (converter.canRead(targetClass, contentType)) {
				if (logger.isDebugEnabled()) {
					logger.debug("Reading [" + targetClass.getName() + "] as \"" +
							contentType + "\" using [" + converter + "]");
				}
				if(converter instanceof HeadBodyJsonHttpMessageConverter){
					Class<?> clas;
					try {
						clas = Class.forName(targetType.getTypeName());
						return  ((HeadBodyJsonHttpMessageConverter<T>) converter).readChildInternal(inputMessage, clas, "body");

					} catch (ClassNotFoundException e) {
						throw new RuntimeException(e);

					}
				}
				
				return ((HttpMessageConverter<T>) converter).read(targetClass, inputMessage);
			}
		}

		throw new HttpMediaTypeNotSupportedException(contentType, this.allSupportedMediaTypes);
	}
	
	
	@Override
	protected <T> Object readWithMessageConverters(NativeWebRequest webRequest, MethodParameter methodParam,
			Type paramType) throws IOException, HttpMediaTypeNotSupportedException {
		final HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
		HttpInputMessage inputMessage = new ServletServerHttpRequest(servletRequest);
			InputStream inputStream = inputMessage.getBody();
			if (inputStream == null) {
				return null;
			}
			else if (inputStream.markSupported()) {
				inputStream.mark(1);
				if (inputStream.read() == -1) {
					return null;
				}
				inputStream.reset();
			}
			else {
				final PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
				int b = pushbackInputStream.read();
				if (b == -1) {
					return null;
				}
				else {
					pushbackInputStream.unread(b);
				}
				inputMessage = new ServletServerHttpRequest(servletRequest) {
					@Override
					public InputStream getBody() throws IOException {
						// Form POST should not get here
						return pushbackInputStream;
					}
				};
			}
			
			
			return readWithMessageConverters(inputMessage, methodParam, paramType);
			
			
	}


	
	

}
