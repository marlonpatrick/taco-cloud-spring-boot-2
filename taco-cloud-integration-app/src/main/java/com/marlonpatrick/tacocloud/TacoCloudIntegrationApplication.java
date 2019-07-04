package com.marlonpatrick.tacocloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class TacoCloudIntegrationApplication {

	public static void main(String[] args) {
		SpringApplication.run(TacoCloudIntegrationApplication.class, args);
	}
	
//	@Bean
//	ApplicationRunner getAppRunner() {
//		return new TacoCloudIntegrationApplicationRunner();
//	}
	
	@Bean
	public RestTemplate restTemplate() {
	  return new RestTemplate();
	}
}