package com.marlonpatrick.tacocloud.order.interfaces.messaging.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.marlonpatrick.tacocloud.order.Order;
import com.marlonpatrick.tacocloud.order.interfaces.messaging.OrderMessagingReceiverGateway;

@Primary
@Component
public class JMSOrderMessagingReceiver implements OrderMessagingReceiverGateway {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	@Transactional
	public void receiveOrder() {
		System.out.println("JMSOrderReceiver...");
		
		Order order = (Order) jmsTemplate.receiveAndConvert("tacocloud.order.queue");
		System.out.println(order);

//		Message message = jmsTemplate.receive("tacocloud.order.queue");
//		System.out.println(message.getClass());
//		try {
//			System.out.println(message.getStringProperty("X_ORDER_SOURCE"));
//		} catch (JMSException e) {
//			throw new RuntimeException(e);
//		}
	}
}
