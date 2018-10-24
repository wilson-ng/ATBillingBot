package com.bot.billing.at.atbillingbot.handler.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.handler.BillHandler;
import com.bot.billing.at.atbillingbot.handler.BotHandler;
import com.bot.billing.at.atbillingbot.handler.UpdateHandler;
import com.bot.billing.at.atbillingbot.model.Bill;
import com.bot.billing.at.atbillingbot.repository.BillRepository;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.Update;

@Component
public class UpdateHandlerImpl implements UpdateHandler {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private List<BotHandler> handlers;
	
	@Autowired
	private List<BillHandler> billHandlers;
	
	@Autowired
	private BillRepository billRepository;

	@Override
	public void handleUpdate(Update update) {
		Message message = update.message();
		
		Long chatId = message.chat().id();
		String textMessage = message.text();
		
		LOG.info(String.format("ChatId: %d, %s", chatId, textMessage)); 
			
		this.registerBillHandler(update, message);
		this.registerBotHandler(update, textMessage);
	}
	
	public void registerBillHandler(Update update, Message message) {
		if (message.replyToMessage() == null) {
			return;
		}
		
		Integer lastMessageId = message.replyToMessage().messageId();
		Bill bill = billRepository.getBillByChatIdAndLastMessageId(message.chat().id(), lastMessageId);
		if (null != bill) {
			for(BillHandler handler : billHandlers) {
				if (handler.canHandle(bill)) {
					handler.handle(bill, update);
					break;
				}
			}	
		}
	}
	
	public void registerBotHandler(Update update, String textMessage) {
		for(BotHandler handler : handlers) {
			if (handler.canHandle(textMessage)) {
				handler.handle(update);
				break;
			}
		}
	}
	
}
