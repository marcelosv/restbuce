package com.br.restbuce.interceptor;

import java.util.Map;

import org.springframework.web.util.UriComponentsBuilder;

import com.br.restbuce.args.ArgQueryParam;
import com.br.restbuce.exceptions.InvalidArgQueryParamException;

public class ProcessQueryParam extends ProcessAbstract implements ProcessExecute<String> {

	private Object[] args;
	private String link;
	private Map<String, Object> queryParams;

	public ProcessQueryParam(Object[] args, String link) {
		this.args = args;
		this.link = link;
	}

	public String execute() {
		
		queryParams = executeInternal();
		if( queryParams == null ){
			return link;
		}
		
		UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(link);
		
		for (Map.Entry<String,Object> entry : queryParams.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			
			if( value == null ){
				continue;
			}
			
			builder.queryParam(key, value);
		}
		
		return builder.toUriString();
	}
	
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Map<String, Object> executeInternal() {
		
		validQueryParams();
		
		ArgQueryParam argQuery = (ArgQueryParam) getArg(ArgQueryParam.class, args);
		
		if( argQuery == null ){
			return null;
		}
		
		return argQuery.getEntity();
	}

	private void validQueryParams() {
		
		if( args== null ){
			return;
		}
		
		int count = 0;
		for (Object arg : args) {

			if (arg.getClass().equals(ArgQueryParam.class)) {
				count++;
			}
			
		}
		
		if( count > 1 ){
			throw new InvalidArgQueryParamException();
		}
	}

	public Map<String, Object> getQueryParams() {
		return queryParams;
	}

}
