package com.br.restbuce.test.intercept.resources;

import org.springframework.http.HttpMethod;

import com.br.restbuce.annotations.Rest;
import com.br.restbuce.args.ArgEntity;
import com.br.restbuce.args.ArgPathParam;
import com.br.restbuce.args.ArgQueryParam;
import com.br.restbuce.repository.RestRepository;
import com.br.restbuce.test.intercept.bean.Nome;

public interface RepositoryLocal extends RestRepository {
	
	@Rest(endPoint="/endpoint1", method=HttpMethod.GET)
	String test();
	
	@Rest(endPoint="/endpoint2", method=HttpMethod.POST)
	String test2(ArgEntity<Nome> nome);
	
	@Rest(endPoint="/endpoint3/{0}/", method=HttpMethod.POST)
	String test3(ArgEntity<Nome> nome, ArgPathParam<String> params);
	
	@Rest(endPoint="/endpoint3/{0}/{1}", method=HttpMethod.POST)
	String test4(ArgEntity<Nome> nome, ArgPathParam<String> params);
	
	@Rest(endPoint="/endpoint-test-error-404", method=HttpMethod.GET)
	String test5();
	
	@Rest(endPoint="/endpoint4", method=HttpMethod.GET)
	String test6(ArgQueryParam<String> args);
	
}