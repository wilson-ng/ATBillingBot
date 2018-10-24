package com.bot.billing.at.atbillingbot.handler.impl.bill;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.billing.at.atbillingbot.handler.BillHandler;
import com.bot.billing.at.atbillingbot.service.BillService;
import com.bot.billing.at.atbillingbot.service.BotService;
import com.bot.billing.at.atbillingbot.service.MemberService;

public abstract class BillHandlerImpl implements BillHandler {

	@Autowired
	protected BotService botService;
	
	@Autowired
	protected BillService billService;
	
	@Autowired
	protected MemberService memberService;
}
