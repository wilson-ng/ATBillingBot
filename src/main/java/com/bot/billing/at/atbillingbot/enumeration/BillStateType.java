package com.bot.billing.at.atbillingbot.enumeration;

public enum BillStateType {
	
	INPUT_VENUE,
	INPUT_PAYER,
	INPUT_SUBTOTAL,
	INPUT_SERVICE_CHARGE,
	INPUT_TAX,
	FINISH;
	
	@Override
	public String toString() {
		return name().toLowerCase();
	}
}
