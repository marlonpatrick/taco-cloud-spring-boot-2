package com.marlonpatrick.tacocloud.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
class KafkaOrderMessagingSender implements OrderMessagingSenderGateway {

	@Autowired
	private KafkaTemplate<String, Order> kafkaTemplate;
	
	@Override
	public void sendOrder(Order order) {
		
		System.out.println(kafkaTemplate.getMessageConverter());
		
		ListenableFuture<SendResult<String,Order>> send = this.kafkaTemplate.send("tacocloud.order.topic", order);
		send.addCallback(result -> System.out.println(result.getProducerRecord().headers()), t -> t.printStackTrace());
	}
}
