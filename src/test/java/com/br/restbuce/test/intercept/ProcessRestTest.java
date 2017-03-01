package com.br.restbuce.test.intercept;

import java.lang.reflect.Method;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.http.HttpMethod;

import com.br.restbuce.annotations.IgnoreRest;
import com.br.restbuce.annotations.Rest;
import com.br.restbuce.args.ArgEntity;
import com.br.restbuce.args.ArgHeader;
import com.br.restbuce.args.ArgPathParam;
import com.br.restbuce.args.ArgQueryParam;
import com.br.restbuce.exceptions.InvalidArgEntityException;
import com.br.restbuce.exceptions.InvalidArgHeaderException;
import com.br.restbuce.exceptions.InvalidArgQueryParamException;
import com.br.restbuce.exceptions.InvalidArgsPathParamException;
import com.br.restbuce.interceptor.ProcessRest;
import com.br.restbuce.repository.RestRepository;

public class ProcessRestTest {

	private static final String HOST = "http://localhost:8080";
	private static final String HOST2 = "http://localhost:8080/";

	@IgnoreRest
	static interface TestRest extends RestRepository{
		@Rest(endPoint="/method1", method=HttpMethod.GET)
		String method1();
		
		@Rest(endPoint="method2", method=HttpMethod.GET)
		String method2();
		
		@Rest(endPoint="", method=HttpMethod.GET)
		String method3();
		
		@Rest(endPoint="/method4/{0}/{1}", method=HttpMethod.GET)
		String method4(ArgPathParam<String> args);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.POST)
		String method5(ArgEntity<String> nome);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.POST)
		String method6(ArgEntity<String> nome, ArgEntity<String> nome2);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.GET)
		String method7(ArgEntity<String> nome);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.DELETE)
		String method8(ArgEntity<String> nome);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.GET)
		String method9(ArgQueryParam<String> args);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.GET)
		String method10(ArgQueryParam<String> args, ArgQueryParam<String> args2);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.GET)
		String method11(ArgHeader<String> args);
		
		@Rest(endPoint="/endpoint3/", method=HttpMethod.GET)
		String method12(ArgHeader<String> args, ArgHeader<String> args2);
	}
	
	@Test(expected=InvalidArgHeaderException.class)
	public void testGetHeaderInvalid() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method12", ArgHeader.class, ArgHeader.class);
		
		ArgHeader<String> args = new ArgHeader<String>();
		args.add("name", "value");
		
		new ProcessRest(HOST, method, new Object[]{args, args});
	}
	
	@Test
	public void testGetHeader() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method11", ArgHeader.class);
		
		ArgHeader<String> args = new ArgHeader<String>();
		args.add("name", "value");
		
		ProcessRest processRest = new ProcessRest(HOST, method, new Object[]{args});
		Assert.assertNotNull(processRest.getArgHeaders());
		Assert.assertEquals(1, processRest.getArgHeaders().size());
	}
	
	@Test(expected=InvalidArgQueryParamException.class)
	public void testGetQueryParamInvalid() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method10", ArgQueryParam.class, ArgQueryParam.class);
		
		ArgQueryParam<String> args = new ArgQueryParam<String>();
		args.add("name", "value");
		
		new ProcessRest(HOST, method, new Object[]{args, args});
	}
	
	@Test
	public void testGetQueryParam() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method9", ArgQueryParam.class);
		
		ArgQueryParam<String> args = new ArgQueryParam<String>();
		args.add("name", "value");
		
		ProcessRest processRest = new ProcessRest(HOST, method, new Object[]{args});
		Assert.assertNotNull(processRest.getQueryParams());
		Assert.assertEquals(1, processRest.getQueryParams().size());
	}
	
	@Test(expected=InvalidArgEntityException.class)
	public void testPostInvalidDELETE() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method8", ArgEntity.class);
		
		ArgEntity<String> nome = new ArgEntity<String>("value");
		new ProcessRest(HOST, method, new Object[]{nome, nome});
	}
	
	@Test(expected=InvalidArgEntityException.class)
	public void testPostInvalidGET() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method7", ArgEntity.class);
		
		ArgEntity<String> nome = new ArgEntity<String>("value");
		new ProcessRest(HOST, method, new Object[]{nome, nome});
	}
	
	@Test(expected=InvalidArgEntityException.class)
	public void testPostInvalid() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method6", ArgEntity.class, ArgEntity.class);
		
		ArgEntity<String> nome = new ArgEntity<String>("value");
		new ProcessRest(HOST, method, new Object[]{nome, nome});
	}
	
	@Test(expected=InvalidArgEntityException.class)
	public void testPostInvalid2() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method6", ArgEntity.class, ArgEntity.class);
		
		ArgEntity<String> nome = new ArgEntity<String>("value");
		new ProcessRest(HOST, method, new Object[]{nome});
	}
	
	@Test
	public void testPost() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method5", ArgEntity.class);
		
		ArgEntity<String> nome = new ArgEntity<String>("value");
		
		ProcessRest processRest = new ProcessRest(HOST, method, new Object[]{nome});
		
		Assert.assertTrue(processRest.getEntity().getBody() instanceof String);
		Assert.assertEquals("value", processRest.getEntity().getBody());
	}
	
	@Test
	public void testLink() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method1");
		
		ProcessRest processRest = new ProcessRest(HOST, method, null);
		Assert.assertEquals(HOST.concat("/method1"), processRest.getLink());
		
		processRest = new ProcessRest(HOST2, method, null);
		Assert.assertEquals(HOST.concat("/method1"), processRest.getLink());
	}
	
	@Test
	public void testLink2() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method2");
		
		ProcessRest processRest = new ProcessRest(HOST, method, null);
		Assert.assertEquals(HOST.concat("/method2"), processRest.getLink());
		
		processRest = new ProcessRest(HOST2, method, null);
		Assert.assertEquals(HOST.concat("/method2"), processRest.getLink());
	}
	
	@Test
	public void testLink3() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method3");
		
		ProcessRest processRest = new ProcessRest(HOST, method, null);
		Assert.assertEquals(HOST.concat("/"), processRest.getLink());
		
		processRest = new ProcessRest(HOST2, method, null);
		Assert.assertEquals(HOST.concat("/"), processRest.getLink());
	}
	
	@Test
	public void testArgParam() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method4", ArgPathParam.class);

		ArgPathParam<String> args = new ArgPathParam<String>();
		args.add("id");
		args.add("login");
		
		ProcessRest processRest = new ProcessRest(HOST, method, new Object[]{args});
		Assert.assertEquals(HOST.concat("/method4/id/login"), processRest.getLink());
	}
	
	@Test(expected=InvalidArgsPathParamException.class)
	public void testArgParamInvalid() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method4", ArgPathParam.class);

		ArgPathParam<String> args = new ArgPathParam<String>();
		args.add("id");
		
		new ProcessRest(HOST, method, new Object[]{args});
	}
	
	@Test(expected=InvalidArgsPathParamException.class)
	public void testArgParamInvalid2() throws NoSuchMethodException, SecurityException{
		Method method = TestRest.class.getMethod("method4", ArgPathParam.class);

		ArgPathParam<String> args = new ArgPathParam<String>();
		args.add("id");
		args.add("id");
		args.add("id");
		
		new ProcessRest(HOST, method, new Object[]{args});
	}
}
