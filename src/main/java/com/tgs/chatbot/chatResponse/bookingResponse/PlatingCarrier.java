package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlatingCarrier {

	private String code;
	private String name;
	private boolean isLcc;
}
