package com.bot.billing.at.atbillingbot.handler;

import com.pengrad.telegrambot.model.Update;

public interface BotHandler {

	public void handle(Update update);
	
	public Boolean canHandle(String stateType);
}
