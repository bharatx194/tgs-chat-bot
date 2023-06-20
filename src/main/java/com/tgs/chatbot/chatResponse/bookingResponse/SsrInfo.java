package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SsrInfo {

	@JsonProperty("BAGGAGE") private ArrayList<Baggage> baggage;
	@JsonProperty("MEAL") private ArrayList<Meal> meal;
	
}
