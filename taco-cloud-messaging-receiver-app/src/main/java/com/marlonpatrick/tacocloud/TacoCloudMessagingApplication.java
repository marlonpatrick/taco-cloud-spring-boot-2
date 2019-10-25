package com.marlonpatrick.tacocloud;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TacoCloudMessagingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudMessagingApplication.class, args);
	}

	@Bean
	ApplicationRunner getAppRunner() {
		return new TacoCloudMessagingApplicationRunner();
	}
}
