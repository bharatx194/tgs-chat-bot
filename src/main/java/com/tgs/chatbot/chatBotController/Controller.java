package com.tgs.chatbot.chatBotController;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tgs.chatbot.chatbotServices.ChatbotServiceImpl;
import com.tgs.chatbot.dbmodel.request.ChatGptRequest;
import com.tgs.chatbot.dbmodel.request.TripJackModelChat;
import com.tgs.chatbot.dbmodel.response.BaseResponse;
import com.tgs.chatbot.dbmodel.response.SystemError;

@RestController
@RequestMapping("/fms/v1/chatbot")
public class Controller {

	private ChatbotServiceImpl chatbotService;

	public Controller(ChatbotServiceImpl chatbotService) {
		super();
		this.chatbotService = chatbotService;
	}

	@PostMapping("/generatChat")
	public BaseResponse generatChat(@RequestBody ChatGptRequest chatRequest) throws Exception {
		BaseResponse responseStatus = new BaseResponse(SystemError.OK);
		responseStatus.setResponse(chatbotService.generalChat(chatRequest));
		return responseStatus;
	}

	@PostMapping("/chatRequest")
	public BaseResponse chatRequest(@RequestBody ChatGptRequest chatRequest, HttpServletRequest httpServletRequest)
			throws Exception {
		BaseResponse responseStatus = new BaseResponse(SystemError.OK);
		responseStatus.setResponse(chatbotService.chatRequest(chatRequest, httpServletRequest));
		return responseStatus;
	}
	
	@PostMapping("/tripjackModelChat")
	public BaseResponse tripjackModelChat(@RequestBody TripJackModelChat tripJackModelChat, HttpServletRequest httpServletRequest)
			throws Exception {
		BaseResponse responseStatus = new BaseResponse(SystemError.OK);
		responseStatus.setResponse(chatbotService.tripjackModelChat(tripJackModelChat, httpServletRequest));
		return responseStatus;
	}
	
	@PostMapping("/tripjackModelChatGeneral")
	public BaseResponse tripjackModelChatGeneral(@RequestBody TripJackModelChat tripJackModelChat, HttpServletRequest httpServletRequest)
			throws Exception {
		BaseResponse responseStatus = new BaseResponse(SystemError.OK);
		responseStatus.setResponse(chatbotService.tripjackModelChatGeneral(tripJackModelChat, httpServletRequest));
		return responseStatus;
	}
}
