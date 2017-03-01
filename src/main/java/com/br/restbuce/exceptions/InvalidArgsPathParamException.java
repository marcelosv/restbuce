package com.br.restbuce.exceptions;

public class InvalidArgsPathParamException extends RuntimeException {

	public InvalidArgsPathParamException(int countArgsLink, int size) {
		super("The link of endpoint have "
				.concat(String.valueOf(countArgsLink))
				.concat(" but the args have ")
				.concat(String.valueOf(size))
				.concat(".") 
		);
	}

	private static final long serialVersionUID = -7749027629048759280L;

}
