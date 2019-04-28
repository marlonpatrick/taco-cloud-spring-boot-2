package com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.kitchen.order.Order;

@Component
public class MultiBrokerOrderMessagingListener {

	@JmsListener(destination="tacocloud.order.queue")
	@RabbitListener(queues="tacocloud.order.queue")
	public void receiveOrder(Order order) {
		System.out.println("OrderListener...");
		System.out.println(order);
	}
}
