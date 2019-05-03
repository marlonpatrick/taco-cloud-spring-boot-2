package com.marlonpatrick.tacocloud;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

class TacoCloudIntegrationApplicationRunner implements ApplicationRunner {
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("TacoCloudIntegrationApplicationRunner started");
		System.out.println("TacoCloudIntegrationApplicationRunner finished");
	}
}
