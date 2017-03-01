package com.br.restbuce.test.intercept.resources;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionMapper {

	@ExceptionHandler
    @ResponseBody
	public ResponseEntity<String> handleConstraintViolation(NotFoundTestException ex) {
        return new ResponseEntity("Mensagem", HttpStatus.NOT_FOUND);
	}
	
}
