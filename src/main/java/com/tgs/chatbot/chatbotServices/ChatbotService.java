package com.tgs.chatbot.chatbotServices;

import javax.servlet.http.HttpServletRequest;

import com.tgs.chatbot.dbmodel.request.ChatGptRequest;

public interface ChatbotService {

	public Object generalChat(ChatGptRequest chatRequest) throws Exception;

	public Object chatRequest(ChatGptRequest chatRequest, HttpServletRequest httpServletRequest) throws Exception;

}
