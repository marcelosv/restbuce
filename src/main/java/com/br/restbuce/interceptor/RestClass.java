package com.br.restbuce.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import com.br.restbuce.repository.RestRepository;

public class RestClass {

	public static Object create(Class<? extends RestRepository> interfaceClass){
		
		Object robot = (RestRepository) java.lang.reflect.Proxy.newProxyInstance(
				interfaceClass.getClassLoader(),
                new java.lang.Class[] { interfaceClass },
                new java.lang.reflect.InvocationHandler(){

					@Autowired
					private ApplicationContext applicationContext;
					
	            @SuppressWarnings("rawtypes")
				@Override
	            public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws java.lang.Throwable {
					ApplicationContext staticContext = ContextStatic.getStaticContext();

					String host = staticContext.getEnvironment().getProperty("restbuce.server");
					ProcessRest processRest = new ProcessRest(host, method, args);
					
					System.out.println(processRest.getLink());
					
					ResponseEntity objectReturn = processRest.execute();
					
					return objectReturn.getBody();
	            }
	        });
		
		return robot;
	}
}
