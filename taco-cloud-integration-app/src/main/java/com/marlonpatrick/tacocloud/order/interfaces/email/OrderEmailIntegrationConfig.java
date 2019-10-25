package com.marlonpatrick.tacocloud.order.interfaces.email;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.dsl.SourcePollingChannelAdapterSpec;
import org.springframework.integration.mail.dsl.ImapMailInboundChannelAdapterSpec;
import org.springframework.integration.mail.dsl.Mail;

@Configuration
class OrderEmailIntegrationConfig {

	@Bean
	IntegrationFlow tacoOrderEmailFlow(EmailProperties emailProperties, EmailToOrderTransformer emailToOrderTransformer,
			OrderSubmitMessageHandler orderSubmitMessageHandler) {
		
		return IntegrationFlows
				.from(imapMail(emailProperties), fixedDelayPoller(emailProperties))
				.transform(emailToOrderTransformer)
				.handle(orderSubmitMessageHandler)
				.get();
	}

	private Consumer<SourcePollingChannelAdapterSpec> fixedDelayPoller(EmailProperties emailProperties) {
		return e -> e.poller(Pollers.fixedDelay(emailProperties.getPollRate()));
	}

	private ImapMailInboundChannelAdapterSpec imapMail(EmailProperties emailProperties) {
		return Mail.imapInboundAdapter(emailProperties.getImapUrl());
	}
}
