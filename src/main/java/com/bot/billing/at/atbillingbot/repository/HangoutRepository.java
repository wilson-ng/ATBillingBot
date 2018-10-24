package com.bot.billing.at.atbillingbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.billing.at.atbillingbot.model.Hangout;
import com.bot.billing.at.atbillingbot.model.Member;

public interface HangoutRepository extends JpaRepository<Hangout, Integer> {

	public Hangout findByCreatedByAndIsDoneTrue(Member createdBy);
	public Hangout findByChatIdAndIsDoneFalse(Long chatId);
}
