package com.marlonpatrick.tacocloud.kitchen.order.interfaces.messaging.jms;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import com.marlonpatrick.tacocloud.kitchen.order.Order;

@Component
public class JMSOrderMessagingListener {

	@JmsListener(destination="tacocloud.order.queue")
	public void receiveOrder(Order order) {
		System.out.println("OrderListener...");
		System.out.println(order);
	}
}
