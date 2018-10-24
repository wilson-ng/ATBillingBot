package com.bot.billing.at.atbillingbot.handler.impl.bill;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.helper.Formatter;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class InputTaxBillHandlerImpl extends BillHandlerImpl {

	@Override
	public void handle(Bill bill, Update update) {
		Long chatId = update.message().chat().id();
		
		Integer tax = Formatter.toInteger(update.message().text());
		if (null == tax) {
			botService.sendMessage(chatId, "Format tax not valid, reply again previous chat.");
			return;
		}
		
		Message message = botService.sendMessageWithKeyboardMembers(chatId, "Input payer of this bill!", new ArrayList<>(bill.getHangout().getMembers()));
		if (null != message) {
			billService.updateTax(bill, tax, message.messageId(), BillStateType.INPUT_PAYER.toString());
		}
	}

	@Override
	public Boolean canHandle(Bill bill) {
		return BillStateType.INPUT_TAX.toString().equals(bill.getNextState());
	}
}
