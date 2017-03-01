package com.br.restbuce.interceptor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ContextStatic {

	@Autowired
	private ApplicationContext context;
	private static ApplicationContext staticContext;
	
	@PostConstruct
	public void setup(){
		staticContext = context;
	}

	static ApplicationContext getStaticContext() {
		return staticContext;
	}

}
