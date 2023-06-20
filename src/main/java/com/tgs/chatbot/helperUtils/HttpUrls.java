package com.tgs.chatbot.helperUtils;

public enum HttpUrls {
	
	BOOKING_DETAILS("https://staging.technogramsolutions.com/oms/v1/booking-details"),
	
	AMENDMENT_DETAILS(""),
	
	USER_DETAILS(""),
	
	FARE_RULE_DETAILS("");
	
	private String url;
	
	HttpUrls(String url){
		this.url = url;
	}
	
	public String getUrl() {
		return this.url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}

}
