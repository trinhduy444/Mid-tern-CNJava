package com.example.SpringCommerceShop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

//@SpringBootApplication
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SpringCommerceShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringCommerceShopApplication.class, args);
	}

}
