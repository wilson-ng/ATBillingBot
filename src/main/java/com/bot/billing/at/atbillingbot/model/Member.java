package com.bot.billing.at.atbillingbot.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.pengrad.telegrambot.model.ChatMember;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"items", "hangouts"})
public class Member implements Serializable {

	private static final long serialVersionUID = 1183911515649636975L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@Column(name = "username")
	private String username;
	
	@Column(name = "user_id")
	private Integer userId;
	
	@Column(name = "chat_id")
	private Long chatId;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "members")
	private Set<Item> items;
	
	@ManyToMany(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.MERGE }, mappedBy = "members")
	private Set<Hangout> hangouts;
	
	public Member(Long chatId, ChatMember chatMember) {
		this.items = new HashSet<>();
		this.hangouts = new HashSet<>();
		this.chatId = chatId;
		this.username = chatMember.user().username();
		this.userId = chatMember.user().id();
	}
	
	@Override
	public String toString() {
		return String.format("Member, chat_id: %d, username: %s, userId: %d", this.chatId, this.username, this.userId);
	}
}
