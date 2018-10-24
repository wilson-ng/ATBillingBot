package com.bot.billing.at.atbillingbot.handler.impl.bill;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class InputVenueBillHandlerImpl extends BillHandlerImpl {

	@Override
	public void handle(Bill bill, Update update) {
		Long chatId = update.message().chat().id();
		Message message = botService.sendMessage(chatId, "Input amount of subtotal!");
		if (null != message) {
			billService.updateVenue(bill, update.message().text(), message.messageId(), BillStateType.INPUT_SUBTOTAL.toString());
		}
	}

	@Override
	public Boolean canHandle(Bill bill) {
		return BillStateType.INPUT_VENUE.toString().equals(bill.getNextState());
	}
}
