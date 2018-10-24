package com.bot.billing.at.atbillingbot.handler.impl.bot;

import org.springframework.beans.factory.annotation.Autowired;

import com.bot.billing.at.atbillingbot.handler.BotHandler;
import com.bot.billing.at.atbillingbot.service.BillService;
import com.bot.billing.at.atbillingbot.service.BotService;
import com.bot.billing.at.atbillingbot.service.HangoutService;
import com.bot.billing.at.atbillingbot.service.MemberService;

public abstract class BotHandlerImpl implements BotHandler {

	@Autowired
	protected HangoutService hangoutService;
	
	@Autowired
	protected MemberService memberService;
	
	@Autowired
	protected BillService billService;
	
	@Autowired
	protected BotService botService;
}
