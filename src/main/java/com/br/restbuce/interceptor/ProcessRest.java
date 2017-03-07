package com.br.restbuce.interceptor;

import java.lang.reflect.Method;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import com.br.restbuce.annotations.Rest;
import com.br.restbuce.component.HeadersInjectRequest;

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
	private Map<String, HeadersInjectRequest> headersInjectRequest;
	private String nameMicroService;
	
	public ProcessRest(String host, Method method, Object[] args, Map<String, HeadersInjectRequest> headersInjectRequest) {
		this.host = host;
		this.method = method;
		this.args = args;
		this.headersInjectRequest = headersInjectRequest;
		
		getAnnotationRest();
		processReturn();
		processLink();
		processEntity();
		processQueryparam();
		processHeader();
		processHeaderInject();
	}

	@Override
	public ResponseEntity execute() {
		return new ProcessSend(this).execute();
	}
	
	private void processHeaderInject() {
		new ProcessHeaderInject(headersInjectRequest, argHeaders).execute();
	}

	private void processHeader() {
		argHeaders = new ProcessHeader(args, headers).execute();
	}

	private void processQueryparam() {
		ProcessQueryParam processQueryParam = new ProcessQueryParam(args, link); 
		link = processQueryParam.execute();
		queryParams = processQueryParam.getQueryParams();
	}

	private void processEntity() {
		entity = new ProcessEntity(args, headers, method, rest).execute();
	}

	private void processLink() {
		String[] nLink = new ProcessLink(host, rest, args, method).execute();
		link = nLink[0];
		
		nameMicroService = nLink.length > 1 ? nLink[1] : ""; 
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

	public String getNameMicroService() {
		return nameMicroService;
	}

	

}
