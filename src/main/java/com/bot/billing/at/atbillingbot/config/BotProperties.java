package com.bot.billing.at.atbillingbot.config;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties("bot")
@Getter
@Setter
public class BotProperties {
	
	private String apiKey;
}
