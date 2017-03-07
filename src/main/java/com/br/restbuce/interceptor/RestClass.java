package com.br.restbuce.interceptor;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.ResponseEntity;

import com.br.restbuce.component.HeadersInjectRequest;
import com.br.restbuce.component.InterceptNameMicroserviceRequest;
import com.br.restbuce.repository.RestRepository;

public class RestClass {

	private static final String RESTBUCE_SERVER = "restbuce.server";
	private static final int RETURN_0 = 0;
	private static final String EQUALS = "equals";
	private static final String HASH_CODE = "hashCode";
	private static final Logger LOG = LoggerFactory.getLogger(RestClass.class);
	
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

					if( HASH_CODE.equals(method.getName()) || EQUALS.equals(method.getName())){
						return RETURN_0;
					}
					
					Map<String, HeadersInjectRequest> headersInjectRequest = staticContext.getBeansOfType(HeadersInjectRequest.class);
					String host = staticContext.getEnvironment().getProperty(RESTBUCE_SERVER);
					
					ProcessRest processRest = new ProcessRest(host, method, args, headersInjectRequest);
					
					LOG.debug("RestBuce exec request: " + processRest.getLink());
					
					try{
						ResponseEntity objectReturn = processRest.execute();
						return objectReturn.getBody();
					}finally{
						// interceo
						Map<String, InterceptNameMicroserviceRequest> interceptNameMicroserviceRequest = (Map<String, InterceptNameMicroserviceRequest>) staticContext.getBeansOfType(InterceptNameMicroserviceRequest.class);
						if( interceptNameMicroserviceRequest != null ){
							for( String key : interceptNameMicroserviceRequest.keySet() ){
								interceptNameMicroserviceRequest.get(key).execute(processRest.getNameMicroService(), processRest.getLink());
							}
						}
					}
					
	            }
	        });
		
		return robot;
	}
}
