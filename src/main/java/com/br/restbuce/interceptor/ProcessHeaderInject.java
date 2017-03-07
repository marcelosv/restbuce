package com.br.restbuce.interceptor;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;

import com.br.restbuce.component.HeadersInjectRequest;

public class ProcessHeaderInject implements ProcessExecute<Map<String, Object>> {

	private Map<String, HeadersInjectRequest> headersInjectRequest;
	private Map<String, Object> argHeaders;
	private HttpHeaders headers;

	public ProcessHeaderInject(
			Map<String, HeadersInjectRequest> headersInjectRequest,
			Map<String, Object> argHeaders, HttpHeaders headers) {
				this.headersInjectRequest = headersInjectRequest;
				this.argHeaders = argHeaders;
				this.headers = headers;
	}

	/**
	 * 
	 * Case exists header with name equal in "headersInjectRequest" and "argHeaders", 
	 * the controller add the "argHeaders". Is have priority.
	 * 
	 */
	@Override
	public Map<String, Object> execute() {

		if( headersInjectRequest == null ){
			return argHeaders;
		}
		
		for (Map.Entry<String,HeadersInjectRequest> entry : headersInjectRequest.entrySet()) {
			
			HeadersInjectRequest headerInject = entry.getValue();
			 
			if( headerInject.header() == null ){
				continue;
			}
			
			for( String keyHeader: headerInject.header().keySet()){
				String value = headerInject.header().get(keyHeader);
				
				if( StringUtils.isEmpty(value) || argHeaders.containsKey(keyHeader)){
					continue;
				}
				
				argHeaders.put(keyHeader, value);
			}
			
		}
		
		for (Entry<String, Object> key : argHeaders.entrySet()) {
			
			Object value = key.getValue();
			
			List<String> list = new ArrayList<String>();
			list.add(value.toString());
			
			headers.put(key.getKey(), list);
		}
		
		return argHeaders;
	}


}
