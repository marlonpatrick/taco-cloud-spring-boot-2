package com.marlonpatrick.tacocloud.order;

import javax.jms.Message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
class JMSOrderMessagingSender implements OrderMessagingSenderGateway {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Override
	public void sendOrder(Order order) {
		this.jmsTemplate.convertAndSend("tacocloud.order.queue", order, 
				message -> {
						message.setStringProperty("X_ORDER_SOURCE", "WEB");
						return message;
				});
	}
	
	void sendOrder2(Order order) {
		this.jmsTemplate.send("tacocloud.order.queue", session -> {
		
			Message message = session.createObjectMessage(order);
			message.setStringProperty("X_ORDER_SOURCE", "WEB");
			return message;
		});
	}

}
