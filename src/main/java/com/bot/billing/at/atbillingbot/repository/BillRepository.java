package com.bot.billing.at.atbillingbot.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bot.billing.at.atbillingbot.model.Bill;

public interface BillRepository extends JpaRepository<Bill, Integer>{

	public Bill getBillByChatIdAndLastMessageId(Long chatId, Integer lastMessageId);
}
