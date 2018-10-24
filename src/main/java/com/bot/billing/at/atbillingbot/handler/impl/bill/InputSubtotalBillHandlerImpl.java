package com.bot.billing.at.atbillingbot.handler.impl.bill;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.helper.Formatter;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class InputSubtotalBillHandlerImpl extends BillHandlerImpl {

	@Override
	public void handle(Bill bill, Update update) {
		Long chatId = update.message().chat().id();
		
		BigDecimal subtotal = Formatter.toAmount(update.message().text());
		if (null == subtotal) {
			botService.sendMessage(chatId, "Format amount not valid, reply again previous chat.");
			return;
		}
		
		Message message = botService.sendMessage(chatId, "Input amount of service charge!");
		if (null != message) {
			billService.updateSubtotal(bill, subtotal, message.messageId(), BillStateType.INPUT_SERVICE_CHARGE.toString());
		}
	}

	@Override
	public Boolean canHandle(Bill bill) {
		return BillStateType.INPUT_SUBTOTAL.toString().equals(bill.getNextState());
	}

}
