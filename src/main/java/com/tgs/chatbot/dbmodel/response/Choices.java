package com.tgs.chatbot.dbmodel.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class Choices {

	@JsonProperty("message") private Message message;
	
}
