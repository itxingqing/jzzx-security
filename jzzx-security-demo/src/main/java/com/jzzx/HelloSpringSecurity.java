package com.jzzx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class HelloSpringSecurity {
	
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringSecurity.class, args);
	}
	
}
