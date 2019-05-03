package com.marlonpatrick.tacocloud.order.interfaces.email;

import javax.mail.Message;
import javax.mail.MessagingException;

import org.springframework.integration.mail.transformer.AbstractMailMessageTransformer;
import org.springframework.integration.support.AbstractIntegrationMessageBuilder;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.stereotype.Component;

@Component
class EmailToOrderTransformer extends AbstractMailMessageTransformer<Order>{

	@Override
	protected AbstractIntegrationMessageBuilder<Order> doTransform(Message mailMessage) throws Exception {
		System.out.println(mailMessage.getSubject());
		System.out.println(mailMessage.getFrom()[0].toString());
	    Order tacoOrder = processPayload(mailMessage);	    
		return MessageBuilder.withPayload(tacoOrder);
	}

	private Order processPayload(Message mailMessage) throws MessagingException {
		return new Order(mailMessage.getFrom()[0].toString());
	}
}
