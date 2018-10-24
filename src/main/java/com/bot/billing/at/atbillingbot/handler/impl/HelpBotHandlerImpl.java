package com.bot.billing.at.atbillingbot.handler.impl;

import java.util.Arrays;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BotStateType;
import com.pengrad.telegrambot.model.Update;

@Component
public class HelpBotHandlerImpl extends BotHandlerImpl {

	@Override
	public void handle(Update update) {
		Long chatId = update.message().chat().id();
		String[] availableTypes = Arrays.stream(BotStateType.class.getEnumConstants()).map(Enum::toString).toArray(String[]::new);
		
		botService.sendMessage(chatId, String.join("\n", availableTypes));
	}

	@Override
	public Boolean canHandle(String stateType) {
		return stateType.equals(BotStateType.HELP.toString());
	}

}
