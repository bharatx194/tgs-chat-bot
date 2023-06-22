package com.tgs.chatbot.dbmodel.response;


import java.util.List;

import lombok.Data;

@Data
public class TripJackModelResponse {
	
	private List<Choice> choices;

}
