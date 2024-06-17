package com.alexborza;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class BuildingsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BuildingsApplication.class, args);
	}

}
