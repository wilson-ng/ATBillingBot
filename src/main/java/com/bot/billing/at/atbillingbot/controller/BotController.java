package com.bot.billing.at.atbillingbot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bot.billing.at.atbillingbot.handler.UpdateHandler;
import com.pengrad.telegrambot.BotUtils;
import com.pengrad.telegrambot.model.Update;

@RestController
public class BotController {
	
	private final Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private UpdateHandler handler;
	
	@RequestMapping(value = "/webhook", method = RequestMethod.POST)
	public void webhook(HttpEntity<String> httpRequest) {
		Update update = BotUtils.parseUpdate(httpRequest.getBody());
		handler.handleUpdate(update);
	}
}
