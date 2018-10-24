package com.bot.billing.at.atbillingbot.handler.impl.bill;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.helper.Formatter;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class InputServiceChargeBillHandlerImpl extends BillHandlerImpl {

	@Override
	public void handle(Bill bill, Update update) {
		Long chatId = update.message().chat().id();
		
		BigDecimal serviceCharge = Formatter.toAmount(update.message().text());
		if (null == serviceCharge) {
			botService.sendMessage(chatId, "Format amount not valid, reply again previous chat.");
			return;
		}
		
		Message message = botService.sendMessage(chatId, "Input amount of tax!");
		if (null != message) {	
			billService.updateServiceCharge(bill, serviceCharge, message.messageId(), BillStateType.INPUT_TAX.toString());
		}
	}

	@Override
	public Boolean canHandle(Bill bill) {
		return BillStateType.INPUT_SERVICE_CHARGE.toString().equals(bill.getNextState());
	}

}
