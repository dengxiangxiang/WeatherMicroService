package com.dxx.springcloud.weather;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@SpringBootApplication
@EnableConfigServer
public class WeatherconfigserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherconfigserverApplication.class, args);
	}

}
