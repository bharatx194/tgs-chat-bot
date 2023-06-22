package com.tgs.chatbot.dbmodel.request;

import com.tgs.chatbot.helperUtils.ChatGptModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TripJackModelChat {
	
	private String model;
	
	private String prompt;
	
	private Long max_tokens;
	
	private Double temperature;
	
	public void setModel(ChatGptModels chatGptModels) {
		this.model = chatGptModels.getChatGptModel();
	}
	
	public void setModel(String model) {
		this.model = model;
	}

}
