package com.marlonpatrick.tacocloud.config.messaging;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marlonpatrick.tacocloud.order.Order;

@Configuration
public class MessagingConfig {

	@Bean
	public MappingJackson2MessageConverter messageConverter() {
		
		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("Order", Order.class);
		
		MappingJackson2MessageConverter messageConverter = new MappingJackson2MessageConverter();
		messageConverter.setTypeIdPropertyName("_typeId");
		messageConverter.setTypeIdMappings(typeIdMappings);
		
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.findAndRegisterModules();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		
		messageConverter.setObjectMapper(objectMapper);
		
		return messageConverter;
	}
}
