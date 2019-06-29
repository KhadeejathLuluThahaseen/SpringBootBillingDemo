package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication // (scanBasePackages = { "com.example.service.impl",
						// "com.example.service" })
@ComponentScan(basePackages = "com.example.*")
@EnableJpaRepositories(basePackages = { "com.example.dao" })

@EntityScan(basePackages = { "com.example.model" })
public class SpringBootBillingDemo {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootBillingDemo.class, args);
	}

}
