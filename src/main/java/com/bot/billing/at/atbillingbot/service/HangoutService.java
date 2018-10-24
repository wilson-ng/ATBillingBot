package com.bot.billing.at.atbillingbot.service;

import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.model.Hangout;
import com.bot.billing.at.atbillingbot.model.Member;
import com.bot.billing.at.atbillingbot.repository.HangoutRepository;

@Component
public class HangoutService {

	@Autowired
	private HangoutRepository hangoutRepository;
	
	public Hangout getOpenHangoutByChatId(Long chatId) {
		return this.hangoutRepository.findByChatIdAndIsDoneFalse(chatId);
	}
	
	public Hangout createHangout(Long chatId, Member createdBy) {
		Hangout hangout = new Hangout(chatId, createdBy);
		hangoutRepository.save(hangout);

		return hangout;
	}
	
	public Hangout createHangout(Long chatId, Member createdBy, List<Member> members) {
		Hangout hangout = this.createHangout(chatId, createdBy);
		hangout = this.refreshHangoutMembers(hangout, members);

		return hangout;
	}
	
	public Hangout refreshHangoutMembers(Hangout hangout, List<Member> members) {
		hangout.setMembers(new HashSet<>(members));
		hangoutRepository.save(hangout);

		return hangout;
	}
	
	public Hangout endHangout(Hangout hangout) {
		hangout.setIsDone(true);
		hangoutRepository.save(hangout);
		
		return hangout;
	}
}
