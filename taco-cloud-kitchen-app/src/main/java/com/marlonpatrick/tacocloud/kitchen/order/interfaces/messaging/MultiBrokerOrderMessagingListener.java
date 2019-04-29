package com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.kitchen.order.Order;

@Component
class MultiBrokerOrderMessagingListener {

	@JmsListener(destination = "tacocloud.order.queue")
	@RabbitListener(queues = "tacocloud.order.queue")
	@KafkaListener(topics = "tacocloud.order.topic")
	private void receiveOrder(Order order) {
		System.out.println("OrderListener... ");
		System.out.println(order);
	}

//	@KafkaListener(topics = "tacocloud.orders.topic")
//	public void receiveOrder(Order order, ConsumerRecord<String, Order> record) {
//		log.info("Received from partition {} with timestamp {}", record.partition(), record.timestamp());
//
//		System.out.println("OrderListener...");
//		System.out.println(order);
//	}

//	@KafkaListener(topics = "tacocloud.orders.topic")
//	public void receiveOrder(Order order, Message<Order> message) {
//		MessageHeaders headers = message.getHeaders();
//		log.info("Received from partition {} with timestamp {}", headers.get(KafkaHeaders.RECEIVED_PARTITION_ID),
//				headers.get(KafkaHeaders.RECEIVED_TIMESTAMP));
//		System.out.println("OrderListener...");
//		System.out.println(order);
//	}

}
