package com.minhadose.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.minhadose.demo.mapper")
public class MinhaDoseApplication {

	public static void main(String[] args) {
		SpringApplication.run(MinhaDoseApplication.class, args);
	}

}
