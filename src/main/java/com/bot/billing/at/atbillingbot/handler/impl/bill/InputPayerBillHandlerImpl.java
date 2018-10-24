package com.bot.billing.at.atbillingbot.handler.impl.bill;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BillStateType;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.bot.billing.at.atbillingbot.model.Member;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class InputPayerBillHandlerImpl extends BillHandlerImpl {

	@Override
	public void handle(Bill bill, Update update) {
		Message message = update.message();
		Long chatId = message.chat().id();
		
		Member payer = memberService.getMemberByChatIdAndUsername(chatId, message.text());
		billService.updatePayer(bill, payer, 9999, BillStateType.FINISH.toString());
		
		botService.sendMessage(chatId, "Bill finish!, " + bill.toString());
	}

	@Override
	public Boolean canHandle(Bill bill) {
		return BillStateType.INPUT_PAYER.toString().equals(bill.getNextState());
	}

}
