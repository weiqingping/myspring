package com.my.test.dao.aspect;

import java.util.Collection;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/*import com.my.test.dao.entity.Page;
@Component
@Aspect
@Order(1000)
public class RepositoryAspect {

	@Around(value="execution(* com.my.test.dao.interfaces..*.*(..))")
	public Object methodAround(ProceedingJoinPoint pjp) throws Throwable{
		Object result=pjp.proceed();
		MethodSignature sig=(MethodSignature)pjp.getSignature();
		Class<?>returnType=sig.getReturnType();
		if(Page.class.isAssignableFrom(returnType)){
			if(result instanceof Collection){
				Collection list=(Collection)result;
				if(null!=list&&!list.isEmpty()){
					return list.toArray()[0];
				}
				return null;
			}
		}
		
		return result;
		
	}
	
}
*/