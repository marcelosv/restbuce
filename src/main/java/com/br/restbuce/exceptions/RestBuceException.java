package com.br.restbuce.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class RestBuceException extends RuntimeException {

	private static final long serialVersionUID = -2110508363954264484L;
	
	public RestBuceException(HttpClientErrorException ex) {
		super(ex.getResponseBodyAsString() == null ? ex.getMessage(): ex.getResponseBodyAsString(), ex);
	}

	public HttpStatus getStatusCode() {
		return ((HttpClientErrorException)getCause()).getStatusCode();
	}
}
