package com.bot.billing.at.atbillingbot.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.model.Member;
import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.model.ChatMember;
import com.pengrad.telegrambot.model.Message;
import com.pengrad.telegrambot.model.User;
import com.pengrad.telegrambot.model.request.Keyboard;
import com.pengrad.telegrambot.model.request.KeyboardButton;
import com.pengrad.telegrambot.model.request.ReplyKeyboardMarkup;
import com.pengrad.telegrambot.request.GetChatAdministrators;
import com.pengrad.telegrambot.request.GetMe;
import com.pengrad.telegrambot.request.SendMessage;

@Component
public class BotService {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TelegramBot bot;
	
	public User getBot() {
		GetMe requestMe = new GetMe();
		return this.bot.execute(requestMe).user();
	}
	
	public List<ChatMember> getAllAdminMembers(Long chatId) {
		GetChatAdministrators requestAdmins = new GetChatAdministrators(chatId);
		return this.bot.execute(requestAdmins).administrators();
	}
	
	public Message sendMessage(Long chatId, String message) {
		
		LOG.info(String.format("Send message with chatId %d, message: %s", chatId, message));	

		SendMessage request = new SendMessage(chatId, message);
		return bot.execute(request).message();
	}
	
	public Message sendMessageWithKeyboardMembers(Long chatId, String message, List<Member> members) {
		
		LOG.info(String.format("Send message with chatId %d, message: %s", chatId, message));
		
		List<KeyboardButton> buttons = new ArrayList<>();
		members.stream().forEach(member -> {
			buttons.add(new KeyboardButton(member.getUsername()));
		});
		Keyboard keyboardMarkup = new ReplyKeyboardMarkup(buttons.toArray(new KeyboardButton[buttons.size()]));
		
		SendMessage request = new SendMessage(chatId, message)
		        .replyMarkup(keyboardMarkup);
		return bot.execute(request).message();
	}
}
