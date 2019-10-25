package com.marlonpatrick.tacocloud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.marlonpatrick.tacocloud.order.interfaces.messaging.OrderMessagingReceiverGateway;

class TacoCloudMessagingApplicationRunner implements ApplicationRunner {

	@Autowired
	private OrderMessagingReceiverGateway orderMessagingReceiver;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("TacoCloudMessagingApplicationRunner started");
		orderMessagingReceiver.receiveOrder();
		System.out.println("TacoCloudMessagingApplicationRunner finished");
	}
}
