package com.br.restbuce.interceptor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import com.br.restbuce.component.HeadersInjectRequest;
import com.br.restbuce.repository.RestRepository;

public class RestClass {

	private RestClass() {
		
	}
	
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

					if( "hashCode".equals(method.getName()) || "equals".equals(method.getName())){
						return 0;
					}
					
					Map<String, HeadersInjectRequest> headersInjectRequest = staticContext.getBeansOfType(HeadersInjectRequest.class);
					String host = staticContext.getEnvironment().getProperty("restbuce.server");
					
					ProcessRest processRest = new ProcessRest(host, method, args, headersInjectRequest);
					
					System.out.println(processRest.getLink());
					
					ResponseEntity objectReturn = processRest.execute();
					
					return objectReturn.getBody();
	            }
	        });
		
		return robot;
	}
}
