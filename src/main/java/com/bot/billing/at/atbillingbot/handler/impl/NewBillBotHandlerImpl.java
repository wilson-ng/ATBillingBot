package com.bot.billing.at.atbillingbot.handler.impl;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.enumeration.BotStateType;
import com.bot.billing.at.atbillingbot.model.Hangout;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class NewBillBotHandlerImpl extends BotHandlerImpl {

	@Override
	public void handle(Update update) {
		Long chatId = update.message().chat().id();
		
		Hangout openHangout = hangoutService.getOpenHangoutByChatId(chatId);
		if (null != openHangout) {
			Message message = botService.sendMessage(chatId, "Input a venue name!");
			if (null != message) {
				billService.createBill(openHangout, chatId, message.messageId(), BillStateType.INPUT_VENUE.toString());
			}	
		} else {
			botService.sendMessage(chatId, "No open hangout in this room.");
		}
		
	}

	@Override
	public Boolean canHandle(String stateType) {
		return stateType.equals(BotStateType.NEW_BILL.toString());
	}

}
