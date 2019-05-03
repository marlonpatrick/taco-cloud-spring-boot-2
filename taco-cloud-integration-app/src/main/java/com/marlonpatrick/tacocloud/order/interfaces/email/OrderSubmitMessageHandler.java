package com.marlonpatrick.tacocloud.order.interfaces.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.messaging.MessageHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class OrderSubmitMessageHandler implements GenericHandler<Order>{

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private ApiProperties apiProperties;
	
	@Override
	public Object handle(Order order, MessageHeaders headers) {		
		String response = restTemplate.postForObject(apiProperties.getUrl(), order, String.class);
		
		System.out.println("OrderSubmitMessageHandler response\n\n" + response);
		
		return null;
	}
}
