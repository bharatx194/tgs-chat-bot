package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TotalPriceList {

	@JsonProperty("fd") private FareDetail fD;

	@JsonProperty("fareIdentifier") private String fareIdentifier;

	private String id;
	
	@JsonProperty("pc") private PlatingCarrier pc;
}
