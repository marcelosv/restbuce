package com.br.restbuce.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.br.restbuce.annotations.Rest;

@SuppressWarnings("rawtypes")
public class ProcessRest implements ProcessExecute<ResponseEntity>{

	private String host;
	private Method method;
	private Object[] args;
	
	private Class classReturn;
	
	private Rest rest;
	private String link;
	
	private HttpEntity entity;
	
	private HttpHeaders headers = new HttpHeaders();
	private Map<String, Object> queryParams;
	private Map<String, Object> argHeaders;
	
	public ProcessRest(String host, Method method, Object[] args) {
		this.host = host;
		this.method = method;
		this.args = args;
		
		getAnnotationRest();
		processReturn();
		processLink();
		processEntity();
		processQueryparam();
		processHeader();
	}

	private void processHeader() {
		argHeaders = new ProcessHeader(args, headers).execute();
	}

	private void processQueryparam() {
		queryParams = new ProcessQueryParam(args).execute();
		
	}

	private void processEntity() {
		entity = new ProcessEntity(args, headers, method, rest).execute();
	}

	private void processLink() {
		link = new ProcessLink(host, rest, args).execute();
	}

	private void getAnnotationRest() {
		rest = method.getAnnotation(Rest.class);
	}

	private void processReturn() {
		classReturn = method.getReturnType();
	}

	public Class getClassReturn() {
		return classReturn;
	}

	public String getLink() {
		return link;
	}

	public HttpMethod getHttpMethod(){
		return rest.method();
	}
	
	public HttpEntity getEntity() {
		return entity;
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

	public Map<String, Object> getArgHeaders() {
		return argHeaders;
	}

	@Override
	public ResponseEntity execute() {
		return new ProcessSend(this).execute();
	}

}
