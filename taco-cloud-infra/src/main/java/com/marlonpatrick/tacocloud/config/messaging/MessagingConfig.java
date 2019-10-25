package com.marlonpatrick.tacocloud.config.messaging;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.converter.AbstractJavaTypeMapper;
import org.springframework.kafka.support.serializer.JsonSerializer;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.marlonpatrick.tacocloud.order.Order;

@Configuration
class MessagingConfig {

	@Value("${spring.kafka.bootstrap-servers}")
    private String kafkaBootstrapServers;

	/**
	 * If you specify Generics like <String, Order> then it will be specific to KafkaTemplate's with those types set in Generics.
	 */
	@Bean
    @SuppressWarnings("rawtypes")
    public ProducerFactory kafkaProducerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBootstrapServers);
        
        return new DefaultKafkaProducerFactory<>(props, new StringSerializer(), kafkaMessageSerializer());
    }

	private JsonSerializer<Object> kafkaMessageSerializer(){
		Map<String, Class<?>> typeIdMappings = new HashMap<>();
		typeIdMappings.put("Order", Order.class);

		JsonSerializer<Object> jsonSerializer = new JsonSerializer<>(createObjectMapper());		
		AbstractJavaTypeMapper typeMapper = (AbstractJavaTypeMapper)jsonSerializer.getTypeMapper();
		typeMapper.setIdClassMapping(typeIdMappings);
		
		return jsonSerializer;		
	}

//	  You can produce a specific kafkaTemplate for <String, Order> for example.
//    @Bean
//    public KafkaTemplate<String, Order> kafkaTemplate() {
//        return new KafkaTemplate<>(kafkaProducerFactory());
//    }
    	
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
