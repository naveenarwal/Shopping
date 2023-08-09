package com.shs.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@OpenAPIDefinition
@SpringBootApplication
public class ShoppingSiteApplication {

	public static void main(String[] args) {
		SpringApplication.run(ShoppingSiteApplication.class, args);
	}

}
