package com.userService;

import com.userService.service.UserService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableDiscoveryClient
public class A1UserServiceApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(A1UserServiceApplication.class, args);
        System.out.println("user service up...");

        UserService userService = context.getBean(UserService.class);

        userService.getUserWithRatings("3d874127-808c-4163-ba74-69270aed2d96");

    }

}
