package com.tgs.chatbot.dbmodel.request;

import java.util.List;

import com.tgs.chatbot.helperUtils.ChatGptModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptRequest {

	private List<Messages> messages;

	private String model;

	private Double temperature;

	private Long max_tokens;

	public void setModel(String model) {
		this.model = model;
	}

	public void setModel(ChatGptModels chatGptModel) {
		this.model = chatGptModel.getChatGptModel();
	}

}
