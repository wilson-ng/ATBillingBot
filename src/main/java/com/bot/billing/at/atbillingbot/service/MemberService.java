package com.bot.billing.at.atbillingbot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.model.Member;
import com.bot.billing.at.atbillingbot.repository.MemberRepository;
import com.pengrad.telegrambot.model.ChatMember;

@Component
public class MemberService {

	@Autowired
	private MemberRepository memberRepository;
	
	public Member getMemberByChatIdAndUserId(Long chatId, Integer userId) {
		return memberRepository.findByChatIdAndUserId(chatId, userId);
	}
	
	public Member getMemberByChatIdAndUsername(Long chatId, String username) {
		return memberRepository.findByChatIdAndUsername(chatId, username);
	}
	
	public Member createMember(Long chatId, ChatMember chatMember) {
		Member member = new Member(chatId, chatMember);
		
		memberRepository.save(member);
		
		return member;
	}
	
	public List<Member> getAllMembers(Long chatId, Integer botId, List<ChatMember> admins) {
		
		List<Member> members = new ArrayList<>();
			
		admins.stream().forEach(admin -> {
			if (!admin.user().id().equals(botId)) {
				Member member = this.getMemberByChatIdAndUserId(chatId, admin.user().id());
				if (null == member) {
					member = this.createMember(chatId, admin);
				}
				members.add(member);
			}
		});
		
		return members;
	}
}
