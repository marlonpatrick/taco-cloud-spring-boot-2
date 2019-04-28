package com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging.rabbit;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.kitchen.order.Order;
import com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging.OrderMessagingReceiverGateway;

@Component
@Primary
public class RabbitOrderMessagingReceiver implements OrderMessagingReceiverGateway {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void receiveOrder() {
		System.out.println("RabbitOrderReceiver...");
		
		Order order = rabbitTemplate.receiveAndConvert("tacocloud.order.queue", new ParameterizedTypeReference<Order>() {});
		System.out.println(order);
	}
}
