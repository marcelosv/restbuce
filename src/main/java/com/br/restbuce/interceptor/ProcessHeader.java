package com.br.restbuce.interceptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import com.br.restbuce.args.ArgHeader;
import com.br.restbuce.exceptions.InvalidArgHeaderException;

public class ProcessHeader extends ProcessAbstract implements ProcessExecute<Map<String, Object>> {

	private Object[] args;
	private HttpHeaders headers;

	public ProcessHeader(Object[] args, HttpHeaders headers) {
		this.args = args;
		this.headers = headers;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> execute() {
		
		validQueryParams();
		
		ArgHeader<Object> argHeader = (ArgHeader) getArg(ArgHeader.class, args);
		
		if( argHeader == null ){
			return new HashMap<String, Object>();
		}
		
		for (String key : argHeader.getEntity().keySet()) {
			List<String> list = new ArrayList<String>();
			list.add(argHeader.getEntity().get(key).toString());
			
			headers.put(key, list);
		}
		
		return argHeader.getEntity();
	}

	private void validQueryParams() {
		
		if( args== null ){
			return;
		}
		
		int count = 0;
		for (Object arg : args) {

			if (arg.getClass().equals(ArgHeader.class)) {
				count++;
			}
			
		}
		
		if( count > 1 ){
			throw new InvalidArgHeaderException();
		}
	}

}
