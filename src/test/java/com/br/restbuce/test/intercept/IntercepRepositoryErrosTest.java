package com.br.restbuce.test.intercept;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.restbuce.config.RestBuceConfig;
import com.br.restbuce.exceptions.RestBuceException;
import com.br.restbuce.interceptor.ContextStatic;
import com.br.restbuce.test.TestApplication;
import com.br.restbuce.test.intercept.resources.EndPointController;
import com.br.restbuce.test.intercept.resources.ExceptionMapper;
import com.br.restbuce.test.intercept.resources.RepositoryLocal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, 
	properties = {"server.port=9878", "restbuce.server=http://localhost:9878" },
	classes = { TestApplication.class, RestBuceConfig.class, ContextStatic.class, EndPointController.class,
		ExceptionMapper.class})
public class IntercepRepositoryErrosTest {

	@Autowired
	private RepositoryLocal repositoryLocal;
	
	@Test
	public void testIntercept() {
		try{
			repositoryLocal.test5();
		}catch (RestBuceException e) {
			Assert.assertEquals(HttpStatus.NOT_FOUND, e.getStatusCode());
		}
	}
		
		
		
}
