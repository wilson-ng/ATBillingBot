package com.bot.billing.at.atbillingbot.handler;

import com.pengrad.telegrambot.response.SendResponse;

public interface SendMessageHandler {

	public SendResponse sendMessage(Long chatId, String message);
}
