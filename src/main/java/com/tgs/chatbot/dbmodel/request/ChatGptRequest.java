package com.tgs.chatbot.dbmodel.request;

import java.util.List;

import lombok.Data;

@Data
public class ChatGptRequest {

	private List<Messages> messages;

	private String model;

}
