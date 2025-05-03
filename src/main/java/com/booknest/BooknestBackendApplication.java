package com.booknest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.booknest.mapper")
public class BooknestBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooknestBackendApplication.class, args);
	}

}
