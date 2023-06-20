package com.tgs.chatbot.dbmodel.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ChatGptResponse {
	
	@JsonProperty("choices") private List<Choices> choicesList;

}
