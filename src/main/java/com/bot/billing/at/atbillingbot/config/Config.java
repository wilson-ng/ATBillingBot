package com.bot.billing.at.atbillingbot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pengrad.telegrambot.TelegramBot;

@Configuration
public class Config {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Bean
	public TelegramBot telegramBbot(BotProperties botProperties) {
		LOG.info(botProperties.getApiKey());
		
		return new TelegramBot(botProperties.getApiKey());
	}
}
