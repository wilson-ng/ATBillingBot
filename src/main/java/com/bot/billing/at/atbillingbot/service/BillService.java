package com.bot.billing.at.atbillingbot.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bot.billing.at.atbillingbot.model.Bill;
import com.bot.billing.at.atbillingbot.model.Hangout;
import com.bot.billing.at.atbillingbot.model.Member;
import com.bot.billing.at.atbillingbot.repository.BillRepository;

@Component
public class BillService {

	@Autowired
	private BillRepository billRepository;
	
	public Bill createBill(Hangout hangout, Long chatId, Integer lastMessageId, String nextState) {
		Bill bill = new Bill(hangout, chatId);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
	
	public Bill updateBill(Bill bill, Integer lastMessageId, String nextState) {
		bill.setLastMessageId(lastMessageId);
		bill.setNextState(nextState);
		
		return bill;
	}
	
	public Bill updateVenue(Bill bill, String venue, Integer lastMessageId, String nextState) {
		bill.setVenue(venue);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
	
	public Bill updateServiceCharge(Bill bill, BigDecimal serviceCharge, Integer lastMessageId, String nextState) {
		bill.setServiceCharge(serviceCharge);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
	
	public Bill updateSubtotal(Bill bill, BigDecimal subtotal, Integer lastMessageId, String nextState) {
		bill.setSubtotal(subtotal);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
	
	public Bill updateTax(Bill bill, Integer tax, Integer lastMessageId, String nextState) {
		bill.setTax(tax);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
	
	public Bill updatePayer(Bill bill, Member payer, Integer lastMessageId, String nextState) {
		bill.setPayer(payer);
		updateBill(bill, lastMessageId, nextState);
		billRepository.save(bill);
		
		return bill;
	}
}
