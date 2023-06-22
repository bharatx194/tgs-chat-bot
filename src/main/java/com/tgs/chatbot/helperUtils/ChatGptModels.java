package com.tgs.chatbot.helperUtils;

public enum ChatGptModels {
	
	CHAT_REQUEST_COMPLETIONS("gpt-3.5-turbo"),
	
	CHAT_MODERATION("text-moderation-001"),
	
	CHAT_EDIT("text-davinci-edit-001"),
	
	BABBAGE("text-babbage-001"),
	
	DAVINCI("text-davinci-001"),
	
	CURIE("text-curie-001"),
	
	TRIPJACK_MODEL("curie:ft-tripjack-2023-06-21-15-46-47");
	
	private String chatGptModel;
	
	ChatGptModels(String chatGptModel){
		this.chatGptModel = chatGptModel;
	}
	
	public String getChatGptModel() {
		return this.chatGptModel;
	}
	
	public void setChatGptModel(String chatGptModel) {
		this.chatGptModel = chatGptModel;
	}
	
}
