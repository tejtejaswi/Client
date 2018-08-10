package com.mycompany;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClientApplicationStarter {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplicationStarter.class, args);
		System.out.println("Spring Boot Application started");
	}
}
