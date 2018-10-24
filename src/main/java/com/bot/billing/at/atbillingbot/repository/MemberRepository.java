package com.bot.billing.at.atbillingbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.billing.at.atbillingbot.model.Member;

public interface MemberRepository extends JpaRepository<Member, Integer>{

	public Member findByChatIdAndUserId(Long chatId, Integer userId);
	public Member findByChatIdAndUsername(Long chatId, String username);
}
