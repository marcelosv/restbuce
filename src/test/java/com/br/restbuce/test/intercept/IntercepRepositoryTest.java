package com.br.restbuce.test.intercept;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.restbuce.args.ArgEntity;
import com.br.restbuce.args.ArgPathParam;
import com.br.restbuce.args.ArgQueryParam;
import com.br.restbuce.config.RestBuceConfig;
import com.br.restbuce.interceptor.ContextStatic;
import com.br.restbuce.test.TestApplication;
import com.br.restbuce.test.intercept.bean.Nome;
import com.br.restbuce.test.intercept.resources.EndPointController;
import com.br.restbuce.test.intercept.resources.RepositoryLocal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, 
	properties = {"server.port=9879", "value=123" },
	classes = { TestApplication.class, RestBuceConfig.class, ContextStatic.class, EndPointController.class })
public class IntercepRepositoryTest {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private RepositoryLocal repositoryLocal2;
	
	@Test
	public void testInterceptQuery() {

		ArgQueryParam args = new ArgQueryParam<String>();
		args.add("field", "fieldtrue");
		
		String endPoint = repositoryLocal2.test6(args);
		
		Assert.assertEquals("fieldtrue", endPoint);
	}
	
	@Test
	public void testIntercept() {

		RepositoryLocal repositoryLocal = (RepositoryLocal) applicationContext
				.getBean(RepositoryLocal.class);
		Assert.assertNotNull(repositoryLocal);
		Assert.assertNotNull(repositoryLocal2);
		
		String endPoint1 = repositoryLocal2.test();
		
		Assert.assertEquals("endPoint1", endPoint1);
	}
	
	@Test
	public void testInterceptPost() {

		Nome nome = new Nome();
		nome.setNomes("marcelo e eu");
		
		ArgEntity<Nome> argEntity = new ArgEntity<Nome>(nome);
		
		String resposta = repositoryLocal2.test2(argEntity);
		Assert.assertEquals("marcelo e eu", resposta);
	}
	
	@Test
	public void testInterceptPostWithPathParam() {

		Nome nome = new Nome();
		nome.setNomes("marcelo e eu");
		
		ArgEntity<Nome> argEntity = new ArgEntity<Nome>(nome);
		
		ArgPathParam<String> paths = new ArgPathParam<String>();
		paths.add("1");
		
		String resposta = repositoryLocal2.test3(argEntity, paths);
		Assert.assertEquals("1", resposta);
		
		paths.add("2");
		resposta = repositoryLocal2.test4(argEntity, paths);
		Assert.assertEquals("2", resposta);
		
	}
	
}


