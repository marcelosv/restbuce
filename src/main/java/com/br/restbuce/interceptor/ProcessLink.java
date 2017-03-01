package com.br.restbuce.interceptor;

import com.br.restbuce.annotations.Rest;
import com.br.restbuce.args.ArgPathParam;
import com.br.restbuce.exceptions.InvalidArgsPathParamException;

public class ProcessLink extends ProcessAbstract implements ProcessExecute<String> {

	private String host;
	private Rest rest;
	private Object[] args;

	public ProcessLink(String host, Rest rest, Object[] args) {
		this.host = host;
		this.rest = rest;
		this.args = args;
	}

	@Override
	public String execute() {
		String link = processLink();
		return processArgs(link);
	}

	@SuppressWarnings("rawtypes")
	private String processArgs(String link) {
		ArgPathParam arg = (ArgPathParam) getArg(ArgPathParam.class, args);
		if (arg == null) {
			return link;
		}

		validArgsParam(link, arg);
		
		String newLink = link;
		for (int loop = 0; loop < arg.size(); loop++) {
			Object item = (String) arg.getEntity(loop);
			newLink = newLink.replace("{".concat(String.valueOf(loop)).concat("}"),
					item.toString());
		}

		return newLink;
	}

	@SuppressWarnings("rawtypes")
	private void validArgsParam(String link, ArgPathParam arg) {
		int countArgsLink = getCountArgsLink(link);
		
		if( countArgsLink != arg.size() ){
			throw new InvalidArgsPathParamException(countArgsLink, arg.size());
		}
	}

	private int getCountArgsLink(String link) {

		int cont = 0;
		int loop = 0;
		boolean valid = true;
		
		while(valid){
			String valueSearch = "{".concat(String.valueOf(loop)).concat("}");
			if( link.indexOf(valueSearch) >= 0 ){
				cont++;
			}else{
				valid = false;
			}
			loop++;
		}
		
		return cont;
	}

	private String processLink() {
		String barra = "";

		String lastCharLink = getLastChar(host);
		String lastCharEndPoint = getFirstChar(rest.endPoint());

		if (!"/".equals(lastCharLink) && !"/".equals(lastCharEndPoint)) {
			barra = "/";
		}

		if ("/".equals(lastCharLink) && "/".equals(lastCharEndPoint)) {
			host = host.substring(0, host.length()-1);
		}
		
		String link = host.concat(barra).concat(rest.endPoint());
		return link;
	}

	
}
