package com.bot.billing.at.atbillingbot.enumeration;

public enum BotStateType {
	
	HELP("/help"),
	START_HANGOUT("/starthangout"),
	UPDATE_HANGOUT("/updatehangout"),
	END_HANGOUT("/endhangout"),
	NEW_BILL("/newbill"),
	CLOSE_BILL("/closebill"),
	NEW_ITEM("/newitem"),
	END_ITEM("/enditem"),
	SHOW_BILL("/showbill"),
	MY_BILL("/mybill");
	
	String value;
	
	private BotStateType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return this.value;
	}
}
