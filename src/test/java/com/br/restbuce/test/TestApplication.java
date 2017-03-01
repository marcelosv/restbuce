package com.br.restbuce.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
public class TestApplication implements CommandLineRunner {

	@Override
	public void run(String... arg0) throws Exception {
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(TestApplication.class, args);
	}
	
}
