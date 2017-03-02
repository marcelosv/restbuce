package com.br.restbuce.component;

import java.util.Map;

/**
 * Interface used for add header in request.
 * 
 */
public interface HeadersInjectRequest {

	Map<String, String> header();
	
}
