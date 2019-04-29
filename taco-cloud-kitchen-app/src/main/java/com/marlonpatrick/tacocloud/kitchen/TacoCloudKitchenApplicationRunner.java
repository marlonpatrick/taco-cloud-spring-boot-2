package com.marlonpatrick.tacocloud.kitchen;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;

import com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging.OrderMessagingReceiverGateway;

class TacoCloudKitchenApplicationRunner implements ApplicationRunner {

	@Autowired
	private OrderMessagingReceiverGateway orderMessagingReceiver;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		System.out.println("TacoCloudKitchenApplicationRunner started");
//		orderMessagingReceiver.receiveOrder();
		System.out.println("TacoCloudKitchenApplicationRunner finished");
	}
}
