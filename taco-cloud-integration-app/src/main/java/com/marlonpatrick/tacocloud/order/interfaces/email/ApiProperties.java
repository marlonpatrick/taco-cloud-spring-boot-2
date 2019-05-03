package com.marlonpatrick.tacocloud.order.interfaces.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("tacocloud.api")
class ApiProperties {

	private String url;
}
