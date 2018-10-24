package com.bot.billing.at.atbillingbot.handler.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BotStateType;
import com.bot.billing.at.atbillingbot.model.Hangout;
import com.bot.billing.at.atbillingbot.model.Member;
import com.pengrad.telegrambot.model.ChatMember;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class StartHangoutBotHandlerImpl extends BotHandlerImpl {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public void handle(Update update) {
		LOG.info("----Start Billing----");
		
		Message message = update.message();
		Long chatId = message.chat().id();
		
		Hangout lastOpenHangout = hangoutService.getOpenHangoutByChatId(chatId);
		
		if (null == lastOpenHangout) {
			List<ChatMember> admins = botService.getAllAdminMembers(chatId);
			List<Member> members = memberService.getAllMembers(chatId, botService.getBot().id(), admins);

			Member createdBy = memberService.getMemberByChatIdAndUserId(chatId, message.from().id());
			Hangout hangout = hangoutService.createHangout(chatId, createdBy, members);

			botService.sendMessage(chatId, hangout.toString() + "\n\n" 
					+ "To new bill : " + BotStateType.NEW_BILL
					+ "\nTo update hangout : " + BotStateType.UPDATE_HANGOUT
					+ "\nTo end hangout : " + BotStateType.END_HANGOUT);
		} else {
			botService.sendMessage(chatId, lastOpenHangout.lastOpenToString());
		}
	}

	@Override
	public Boolean canHandle(String stateType) {
		return stateType.equals(BotStateType.START_HANGOUT.toString());
	}
	
}
