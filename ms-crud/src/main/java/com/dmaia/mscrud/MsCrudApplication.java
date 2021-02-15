package com.dmaia.mscrud;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCrudApplication.class, args);
	}

}
