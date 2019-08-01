package com.bcs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication(scanBasePackages={"com.bcs"})
public class BookStore {

	public static void main(String[] args) {
		SpringApplication.run(BookStore.class, args);
	}
}
