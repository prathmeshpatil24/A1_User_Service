package com.userService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class A1UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(A1UserServiceApplication.class, args);
        System.out.println("user service...");

	}

}
