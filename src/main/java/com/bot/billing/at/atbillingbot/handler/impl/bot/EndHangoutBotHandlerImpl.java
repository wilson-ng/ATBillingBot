package com.bot.billing.at.atbillingbot.handler.impl.bot;

import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.enumeration.BotStateType;
import com.bot.billing.at.atbillingbot.model.Hangout;
import com.bot.billing.at.atbillingbot.model.Member;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class EndHangoutBotHandlerImpl extends BotHandlerImpl {

	@Override
	public void handle(Update update) {
		Message message = update.message();
		Long chatId = message.chat().id();
		
		Hangout lastOpenHangout = hangoutService.getOpenHangoutByChatId(chatId);
		if (null != lastOpenHangout) {
			Member createdBy = memberService.getMemberByChatIdAndUserId(chatId, message.from().id());
			if (null != createdBy && lastOpenHangout.getCreatedBy().equals(createdBy)) {
				hangoutService.endHangout(lastOpenHangout);
				
				botService.sendMessage(chatId, lastOpenHangout.closeToString());	
			} else {
				botService.sendMessage(chatId, "You're not authorize to end current hangout");
			}
		} else {
			botService.sendMessage(chatId, "No open hangout in this room.");
		}
	}

	@Override
	public Boolean canHandle(String stateType) {
		return stateType.equals(BotStateType.END_HANGOUT.toString());
	}

}
