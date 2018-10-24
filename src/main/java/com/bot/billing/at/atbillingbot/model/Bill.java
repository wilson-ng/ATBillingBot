package com.bot.billing.at.atbillingbot.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Bill implements Serializable {

	private static final long serialVersionUID = 187237568002389148L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "payer_id", nullable = true)
	private Member payer;
	
	@Column(name = "venue")
	private String venue;
	
	@Column(name = "subtotal")
	private BigDecimal subtotal;
	
	@Column(name = "service_charge")
	private BigDecimal serviceCharge;
	
	@Column(name = "tax")
	private Integer tax;
	
	@Column(name = "last_message_id")
	private Integer lastMessageId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "hangout_id", nullable = false)
	private Hangout hangout;
	
	@Column(name = "next_state")
	private String nextState;
	
	@Column(name = "chat_id")
	private Long chatId;
	
	public Bill(Hangout hangout, Long chatId) {
		this.hangout = hangout;
		this.chatId = chatId;
	}
	
	@Override
	public String toString() {
		return String.format("Bill%npayer: %s%nvenue: %s%nsubtotal: %.0f%nservice_charge: %.0f%ntax: %d%%",
				this.payer.getUsername(), this.venue, this.subtotal, this.serviceCharge, this.tax);
	}
}
