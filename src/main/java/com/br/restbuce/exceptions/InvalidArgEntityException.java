package com.br.restbuce.exceptions;

public class InvalidArgEntityException extends RuntimeException{

	private static final long serialVersionUID = 5280462396061370978L;

	public InvalidArgEntityException() {
		super("Endpoint with methost POST can not have two or more ArgEntity.");
	}
	
}
