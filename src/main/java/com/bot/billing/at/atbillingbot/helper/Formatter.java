package com.bot.billing.at.atbillingbot.helper;

import java.math.BigDecimal;

public class Formatter {

	public static BigDecimal toAmount(String decimalText) {
		try {
			return new BigDecimal(decimalText);
		} catch (NumberFormatException e) {
			return null;
		}
	}
	
	public static Integer toInteger(String integerText) {
		try {
			return Integer.valueOf(integerText);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
