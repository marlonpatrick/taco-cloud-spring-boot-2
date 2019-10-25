package com.marlonpatrick.tacocloud.order;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
class RabbitOrderMessagingSender implements OrderMessagingSenderGateway {

	@Autowired
	private RabbitTemplate rabbitTemplate;

	@Override
	public void sendOrder(Order order) {		
		this.rabbitTemplate.convertAndSend("tacocloud.order.exchange", "tacocloud.order.queue", order, message -> {
			message.getMessageProperties().setHeader("X_ORDER_SOURCE", "WEB");
			return message;
		});
	}
	
	void sendOrder2(Order order) {
		
		MessageConverter messageConverter = rabbitTemplate.getMessageConverter();
		MessageProperties messageProperties = new MessageProperties();
		messageProperties.setHeader("X_ORDER_SOURCE", "WEB");
		Message message = messageConverter.toMessage(order, messageProperties);
		
		this.rabbitTemplate.send("tacocloud.order.exchange", "tacocloud.order.queue", message);
	}
}
