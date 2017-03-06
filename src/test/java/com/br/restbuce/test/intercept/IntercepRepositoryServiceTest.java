package com.br.restbuce.test.intercept;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.DependsOn;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.restbuce.config.RestBuceConfig;
import com.br.restbuce.interceptor.ContextStatic;
import com.br.restbuce.test.TestApplication;
import com.br.restbuce.test.intercept.resources.EndPointController;
import com.br.restbuce.test.intercept.resources.ExceptionMapper;
import com.br.restbuce.test.intercept.resources.ServiceRest;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.DEFINED_PORT, 
	properties = {"server.port=9877", "restbuce.server=http://localhost:9877" },
	classes = { TestApplication.class, RestBuceConfig.class, ContextStatic.class, EndPointController.class,
		ExceptionMapper.class, ServiceRest.class})
public class IntercepRepositoryServiceTest {

	@Autowired
	private ServiceRest serviceRest;
	
	@Test
	public void testIntercept() {
		serviceRest.exec();
	}
		
		
		
}
