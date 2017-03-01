package com.br.restbuce.test.intercept.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.br.restbuce.test.intercept.bean.Nome;

@RestController
public class EndPointController {

	@RequestMapping(value = "/endpoint1", method = { RequestMethod.GET })
	@ResponseBody
	public String test(){
		return "endPoint1";
	}
	
	@RequestMapping(value = "/endpoint2", method = { RequestMethod.POST })
	@ResponseBody
	public String testPost(@RequestBody Nome nome ){
		return nome.getNomes();
	}
	
	@RequestMapping(value = "/endpoint3/{path1}", method = { RequestMethod.POST })
	@ResponseBody
	public String testPostWithPath(@RequestBody Nome nome, @PathVariable("path1") String path1){
		return path1;
	}
	
	@RequestMapping(value = "/endpoint3/{path1}/{path2}", method = { RequestMethod.POST })
	@ResponseBody
	public String testPostWithPath(@RequestBody Nome nome, @PathVariable("path1") String path1, @PathVariable("path2") String path2){
		return path2;
	}
	
	@RequestMapping(value = "/endpoint-test-error-404", method = { RequestMethod.GET })
	@ResponseBody
	public String testErro(){
		throw new NotFoundTestException();
	}
	
}
