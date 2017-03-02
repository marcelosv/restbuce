package com.br.restbuce.interceptor;

import java.util.Map;

import org.springframework.util.StringUtils;

import com.br.restbuce.component.HeadersInjectRequest;

public class ProcessHeaderInject implements ProcessExecute<Map<String, Object>> {

	private Map<String, HeadersInjectRequest> headersInjectRequest;
	private Map<String, Object> argHeaders;

	public ProcessHeaderInject(
			Map<String, HeadersInjectRequest> headersInjectRequest,
			Map<String, Object> argHeaders) {
				this.headersInjectRequest = headersInjectRequest;
				this.argHeaders = argHeaders;
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
		
		return argHeaders;
	}


}
