package com.marlonpatrick.tacocloud.kitchen;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TacoCloudKitchenApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudKitchenApplication.class, args);
	}

	@Bean
	public ApplicationRunner getAppRunner() {
		return new TacoCloudKitchenApplicationRunner();
	}
}
