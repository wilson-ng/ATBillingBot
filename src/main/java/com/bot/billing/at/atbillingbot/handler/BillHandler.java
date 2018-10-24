package com.bot.billing.at.atbillingbot.handler;

import com.bot.billing.at.atbillingbot.model.Bill;
import com.pengrad.telegrambot.model.Update;

public interface BillHandler {

	public void handle(Bill bill, Update update);
	
	public Boolean canHandle(Bill bill);
}
