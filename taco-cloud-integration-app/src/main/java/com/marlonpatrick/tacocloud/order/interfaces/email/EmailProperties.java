package com.marlonpatrick.tacocloud.order.interfaces.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties("tacocloud.email")
class EmailProperties {

	private String username;
	private String password;
	private String host;
	private String mailbox;	
	private long pollRate = 30000;

	public String getImapUrl() {
		String imapUrl = String.format("imaps://%s:%s@%s/%s", this.username, this.password, this.host, this.mailbox);
		
		System.out.println(imapUrl);
		
		return imapUrl;
	}

}
