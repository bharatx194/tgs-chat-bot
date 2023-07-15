package com.tgs.chatbot.helperUtils;

public enum ChatGptModels {

	CHAT_REQUEST_COMPLETIONS("gpt-3.5-turbo"),
	
	CHAT_REQUEST_COMPLETIONS_TURBO("gpt-3.5-turbo-16k"),

	CHAT_MODERATION("text-moderation-001"),

	CHAT_EDIT("text-davinci-edit-001"),

	DAVINCI("text-davinci-001"),

	TRIPJACK_REFINED_MODEL_1("davinci:ft-tripjack-2023-06-28-12-52-50"),
	
	TRIPJACK_REFINED_MODEL_2("davinci:ft-tripjack-2023-06-28-15-35-56"),
	
	TRIPJACK_REFINED_MODEL_3("davinci:ft-tripjack-2023-06-29-14-10-47");
	

	private String chatGptModel;

	ChatGptModels(String chatGptModel) {
		this.chatGptModel = chatGptModel;
	}

	public String getChatGptModel() {
		return this.chatGptModel;
	}

	public void setChatGptModel(String chatGptModel) {
		this.chatGptModel = chatGptModel;
	}

}
