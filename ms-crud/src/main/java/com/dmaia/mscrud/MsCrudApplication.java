package com.dmaia.mscrud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class MsCrudApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsCrudApplication.class, args);
	}

}
