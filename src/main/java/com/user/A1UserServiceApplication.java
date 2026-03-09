package com.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class A1UserServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(A1UserServiceApplication.class, args);
		System.out.println("user service running..");
	}

}
