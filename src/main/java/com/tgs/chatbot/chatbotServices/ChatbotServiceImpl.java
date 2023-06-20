package com.tgs.chatbot.chatbotServices;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.Callable;
import javax.servlet.http.HttpServletRequest;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgs.chatbot.dbmodel.request.ChatGptRequest;
import com.tgs.chatbot.dbmodel.request.GetBookingDetails;
import com.tgs.chatbot.dbmodel.response.ChatGptResponse;
import com.tgs.chatbot.helperUtils.HttpHelperUtils;

@Service
public class ChatbotServiceImpl implements ChatbotService {

	@Value("${apiKey}")
	private String apiKey;

	private ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,
			false);
	
	
	@Override
	public Object generalChat(ChatGptRequest chatGptRequest) throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		ObjectMapper objectMapper = new ObjectMapper();
		Object jsonObject = objectMapper.readValue(responseBody, Object.class);

		return jsonObject;

	}

	@Override
	public Object chatRequest(ChatGptRequest chatGptRequest, HttpServletRequest httpServletRequest) throws Exception {

		String question = chatGptRequest.getMessages().get(0).getContent();

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String newContent = "Just fetch and return only the bookingid(Characters limit - 20) from - " + question
				+ ". The response should only contain the bookingid without any irrevalent text.";
		chatGptRequest.getMessages().get(0).setContent(newContent);
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		ChatGptResponse gptResponse = mapper
				.readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), ChatGptResponse.class);
		String res = (String) gptResponse.getChoicesList().get(0).getMessage().getContent();
		Object boookingDetails = HttpHelperUtils.getBooking(GetBookingDetails.builder().bookingId(res).build(),
				httpServletRequest);
		String bookingDetail = mapper.writeValueAsString(boookingDetails);

		return genetareResponse(chatGptRequest, bookingDetail, question);
	}

	public String genetareResponse(ChatGptRequest chatGptRequest, String details, String question) throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String newContent = "Here are the details of the booking in json format - " + details + "." + question;
		chatGptRequest.getMessages().get(0).setContent(newContent);
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		ChatGptResponse gptResponse = mapper.readValue(responseBody, ChatGptResponse.class);
		String res = (String) gptResponse.getChoicesList().get(0).getMessage().getContent();
		return res;

	}

	public boolean checkSimilarity(String question1, String question2, ChatGptRequest chatGptRequest) throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String newContent = "Check if the bellow two questions are exactly same and having same IDs mentioned. Return true if they are same and will have a same answer : "
				+ "Question 1: " + question1 + "Question 2: " + question2 + "."
				+ " Just say true Or false without explanations";

		chatGptRequest.getMessages().get(0).setContent(newContent);
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		ChatGptResponse gptResponse = mapper.readValue(responseBody, ChatGptResponse.class);
		String res = (String) gptResponse.getChoicesList().get(0).getMessage().getContent();
		System.out.println(res);
		if (res.contentEquals("True.") || res.contentEquals("true") || res.contentEquals("True"))
			return true;

		return false;

	}

	class Task implements Callable<Boolean> {

		private String storedQuestion, question;

		private ChatGptRequest chatRequest;

		public Task(String storedQuestion, String question, ChatGptRequest chatRequest) {
			this.storedQuestion = storedQuestion;
			this.question = question;
			this.chatRequest = chatRequest;
		}

		@Override
		public Boolean call() throws Exception {

			if (checkSimilarity(storedQuestion, question, chatRequest)) {
				return true;
			}
			return false;
		}

	}

}
