package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = { "com.booknest.example.demo", "com.booknest" })
@MapperScan("com.booknest.mapper")
public class BooknestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooknestBackendApplication.class, args);
	}

}
