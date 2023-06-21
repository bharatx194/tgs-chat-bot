package com.tgs.chatbot.helperUtils;

public enum ChatGptModels {
	
	CHAT_REQUEST_COMPLETIONS("gpt-3.5-turbo"),
	
	CHAT_MODERATION("text-moderation-001"),
	
	CHAT_EDIT("text-davinci-edit-001");
	
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
