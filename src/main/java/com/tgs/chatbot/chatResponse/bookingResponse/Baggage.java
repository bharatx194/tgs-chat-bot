package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Baggage {

	private String code;
	private Double amount;
	private String desc;
}
