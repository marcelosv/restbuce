package com.br.restbuce.interceptor;

import java.util.Map;

import com.br.restbuce.args.ArgQueryParam;
import com.br.restbuce.exceptions.InvalidArgQueryParamException;

public class ProcessQueryParam extends ProcessAbstract implements ProcessExecute<Map<String, Object>> {

	private Object[] args;

	public ProcessQueryParam(Object[] args) {
		this.args = args;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> execute() {
		
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

}
