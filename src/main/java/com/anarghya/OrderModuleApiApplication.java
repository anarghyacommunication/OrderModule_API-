package com.anarghya;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class OrderModuleApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderModuleApiApplication.class, args);
	}

}
