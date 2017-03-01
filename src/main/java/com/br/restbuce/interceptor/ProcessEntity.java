package com.br.restbuce.interceptor;

import java.lang.reflect.Method;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;

import com.br.restbuce.annotations.Rest;
import com.br.restbuce.args.ArgEntity;
import com.br.restbuce.exceptions.InvalidArgEntityException;

@SuppressWarnings("rawtypes")
public class ProcessEntity extends ProcessAbstract implements ProcessExecute<HttpEntity> {

	private Object[] args;
	private HttpHeaders headers;
	private Method method;
	private Rest rest;

	public ProcessEntity(Object[] args, HttpHeaders headers, Method method, Rest rest) {
		this.args = args;
		this.headers = headers;
		this.method = method;
		this.rest = rest;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public HttpEntity execute() {

		validArgEntity(args);
		validArgEntityMethod();
		
		Object arg = getArg(ArgEntity.class, args);
		
		if( arg != null ){
			Object entyPost = ((ArgEntity)arg).getEntity();
			return new HttpEntity(entyPost, headers);
		}
		
		// GET
		return new HttpEntity(headers);
	}

	private void validArgEntityMethod() {
		if( method.getParameterTypes() == null ){
			return;
		}
		
		validArgEntity(method.getParameterTypes());
	}

	private void validArgEntity(Object[] args) {
		
		if (args == null) {
			return;
		}

		int cont = 0;
		for (Object arg : args) {
			if (arg.equals(ArgEntity.class) || arg.getClass().equals(ArgEntity.class)) {
				cont++;
			}
		}
		
		if( cont > 1){
			throw new InvalidArgEntityException();
		}
		
		if( cont == 1 && (rest.method() == HttpMethod.GET || rest.method() == HttpMethod.DELETE) ){
			throw new InvalidArgEntityException();
		}
		
	}

}
