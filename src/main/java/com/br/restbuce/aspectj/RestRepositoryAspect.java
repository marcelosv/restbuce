//package com.br.restbuce.aspectj;
//
//
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//@Aspect
//@Component
//public class RestRepositoryAspect {
//
//	private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());
//
//	public RestRepositoryAspect() {
//	"".toCharArray();
//	}
//	
//	@Around("@annotation(com.br.restbuce.annotations.Rest) && execution(* *(..))")
////	@Around("execution(private void CreateClasseUtil.invoke(..))")
//	public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
//		
//		
//		return null;
//	}
//	
//}
