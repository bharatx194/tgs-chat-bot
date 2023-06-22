package com.tgs.chatbot.chatbotServices;

import javax.servlet.http.HttpServletRequest;

import com.tgs.chatbot.dbmodel.request.ChatGptRequest;
import com.tgs.chatbot.dbmodel.request.TripJackModelChat;

public interface ChatbotService {

	public Object generalChat(ChatGptRequest chatRequest) throws Exception;

	public Object chatRequest(ChatGptRequest chatRequest, HttpServletRequest httpServletRequest) throws Exception;
	
	public Object tripjackModelChat(TripJackModelChat tripjackModelChat, HttpServletRequest hHttpServletRequest) throws Exception;

}
