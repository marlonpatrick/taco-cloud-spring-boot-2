package com.marlonpatrick.tacocloud.kitchen.config.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marlonpatrick.tacocloud.kitchen.order.Order;

@Configuration
class MessagingConfig {

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.findAndRegisterModules();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper;
	}

	@Bean
	MappingJackson2MessageConverter jmsMessageConverter() {
		
		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("Order", Order.class);
		
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("_typeId");
		messageConverter.setTypeIdMappings(typeIdMappings);
		messageConverter.setObjectMapper(createObjectMapper());
		
		
		return messageConverter;
	}

	@Bean
	Jackson2JsonMessageConverter rabbitMessageConverter(){		
		return new Jackson2JsonMessageConverter(createObjectMapper());
	}
}
