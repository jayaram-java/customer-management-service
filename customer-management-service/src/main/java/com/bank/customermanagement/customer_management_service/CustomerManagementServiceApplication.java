package com.bank.customermanagement.customer_management_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication // @Configuraton , @EnableAutoconfiguration, @ComponentScan
@EnableScheduling
public class CustomerManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerManagementServiceApplication.class, args);
	}

}

/*SpringApplication.run() bootstraps the Spring Boot application by creating 
the application context, enabling auto-configuration, 
scanning components, initializing beans, and starting the embedded web server.*/