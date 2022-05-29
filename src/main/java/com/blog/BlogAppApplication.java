package com.blog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BlogAppApplication {

	public static void main(String[] args) {
		System.out.println("Application started!!");
		SpringApplication.run(BlogAppApplication.class, args);
		System.out.println("Application finished!!");
	}

}
