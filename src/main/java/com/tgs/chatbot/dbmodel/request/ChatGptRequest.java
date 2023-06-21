package com.tgs.chatbot.dbmodel.request;

import java.util.List;

import com.tgs.chatbot.helperUtils.ChatGptModels;

import lombok.Data;

@Data
public class ChatGptRequest {

	private List<Messages> messages;

	private String model;
	
	public void setModel(ChatGptModels chatGptModel) {
		this.model = chatGptModel.getChatGptModel();
	}

}
