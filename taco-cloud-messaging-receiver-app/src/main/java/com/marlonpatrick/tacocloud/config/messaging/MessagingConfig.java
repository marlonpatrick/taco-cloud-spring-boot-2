package com.marlonpatrick.tacocloud.config.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.converter.AbstractJavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marlonpatrick.tacocloud.order.Order;

@Configuration
class MessagingConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

	@Value("${spring.kafka.consumer.group-id}")
    private String kafkaConsumerGroup;

	/**
	 * If you specify Generics like <String, Order> then it will be specific to
	 * those types set in Generics.
	 */
	@Bean
	@SuppressWarnings("rawtypes")
	public ConsumerFactory kafkaConsumerFactory() {
		Map<String, Object> props = new HashMap<>();
		props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaConsumerGroup);
        
		return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(), kafkaMessageDeserializer());
	}

	private JsonDeserializer<Object> kafkaMessageDeserializer() {
		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("Order", Order.class);

		JsonDeserializer<Object> jsonSerializer = new JsonDeserializer<>(createObjectMapper());
		AbstractJavaTypeMapper typeMapper = (AbstractJavaTypeMapper) jsonSerializer.getTypeMapper();
		typeMapper.setIdClassMapping(typeIdMappings);

		return jsonSerializer;
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
	Jackson2JsonMessageConverter rabbitMessageConverter() {
		return new Jackson2JsonMessageConverter(createObjectMapper());
	}

	private ObjectMapper createObjectMapper() {
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new JavaTimeModule());
		objectMapper.findAndRegisterModules();
		objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return objectMapper;
	}

}
