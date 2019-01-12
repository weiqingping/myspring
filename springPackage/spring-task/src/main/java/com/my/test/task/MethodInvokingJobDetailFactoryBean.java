package com.my.test.task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.InitializingBean;
import com.my.test.dao.interceptor.Intercepotor;
import com.my.test.task.annotation.MethodName;

public class MethodInvokingJobDetailFactoryBean
		extends org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean implements InitializingBean {
	private static Map<String, Method> methodMapping;
	static {
		synchronized (MethodInvokingJobDetailFactoryBean.class) {
			methodMapping = new HashMap<String, Method>();
		}
	}

	public MethodInvokingJobDetailFactoryBean() {
	}

	private Method methodObject;
	private String filterIp;
	private Date effectTime;
	private Date expireTime;
	private List<Intercepotor> interceptors;
	private DataContainer containers;

	public List<Intercepotor> getInterceptors() {
		return interceptors;
	}

	public void setInterceptors(List<Intercepotor> interceptors) {
		this.interceptors = interceptors;
	}

	public Date getEffectTime() {
		return effectTime;
	}

	public void setEffectTime(Date effectTime) {
		this.effectTime = effectTime;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getFilterIp() {
		return filterIp;
	}

	public void setFilterIp(String filterIp) {
		this.filterIp = filterIp;
	}

	public Method getMethodObject() {
		return methodObject;
	}

	public void setMethodObject(Method methodObject) {
		this.methodObject = methodObject;
	}

	private Map<String, Object> arugumentMap;
	private String methodPath;

	public String getMethodPath() {
		return methodPath;
	}

	public void setMethodPath(String methodPath) {
		this.methodPath = methodPath;
	}

	public Map<String, Object> getArugumentMap() {
		return arugumentMap;
	}

	public void setArugumentMap(Map<String, Object> arugumentMap) {
		this.arugumentMap = arugumentMap;
	}

	@Override
	public void prepare() throws ClassNotFoundException, NoSuchMethodException {

	}

	@Override
	public Method getPreparedMethod() throws IllegalStateException {
		return methodObject;
	}



	@Override
	public Object invoke() throws InvocationTargetException, IllegalAccessException {
		try {
			if (null != interceptors && !interceptors.isEmpty()) {
				for (Intercepotor intercepotor : interceptors) {
                    if(!intercepotor.test(containers))
                    	return null;
				}
			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}
		return super.invoke();
	}

	@Override
	public void afterPropertiesSet() throws ClassNotFoundException, NoSuchMethodException {
		containers=new DataContainer(effectTime, expireTime, filterIp);
		super.afterPropertiesSet();
	}

	private Method getTagetMethod() {
		synchronized (MethodInvokingJobDetailFactoryBean.class) {
			if (methodMapping.containsKey(getMethodPath())) {
				return methodMapping.get(getMethodPath());
			}
			Class<?> targetClass = getTargetClass();
			Method[] methods = targetClass.getDeclaredMethods();
			if (null != methods && methods.length > 0) {
				for (Method method : methods) {
					if (method.isAnnotationPresent(MethodName.class)) {
						MethodName path = method.getAnnotation(MethodName.class);
						if (path.equals(getMethodPath())) {
							methodMapping.put(getMethodPath(), method);
							return method;
						}
					}
				}
			}
		}
		return null;
	}

	

}
