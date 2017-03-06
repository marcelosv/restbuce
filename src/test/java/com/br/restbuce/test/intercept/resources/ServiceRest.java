package com.br.restbuce.test.intercept.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

@Service
@DependsOn(value="RepositoryLocal")
public class ServiceRest {

	@Autowired
	private RepositoryLocal repositoryLocal;
	
	public ServiceRest() {
		"".toCharArray();
	}
	
	public void exec(){
		repositoryLocal.test();
	}
}
