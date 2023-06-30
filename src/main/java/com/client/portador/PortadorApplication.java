package com.client.portador;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class PortadorApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortadorApplication.class, args);
	}

}
