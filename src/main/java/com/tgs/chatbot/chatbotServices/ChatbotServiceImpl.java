package com.tgs.chatbot.chatbotServices;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
import com.tgs.chatbot.dbmodel.request.TripJackModelChat;
import com.tgs.chatbot.dbmodel.response.ChatGptResponse;
import com.tgs.chatbot.dbmodel.response.TripJackModelResponse;
import com.tgs.chatbot.helperUtils.ChatGptModels;
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
		chatGptRequest.setModel(ChatGptModels.CHAT_REQUEST_COMPLETIONS_TURBO);
		String ratingFactors = new String(
				Files.readAllBytes(Paths.get("/Users/bharatjoshi/Downloads/RatingFactors.txt"))),
				chat = new String(Files.readAllBytes(Paths.get("/Users/bharatjoshi/Downloads/Chat4.txt")));
		String newContent = "Here is the converstion between an agent an a customer : " + chat
				+ "Analyse the chat in depth and rate the agent in all the mentioned factors seperately out of 100(without explanations) and also give an overall rating out of 100 : - "
				+ ratingFactors + ". Also write a suggestion or remark for the agent One liner";
		chatGptRequest.getMessages().get(0).setContent(newContent);
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		ChatGptResponse jsonObject = mapper.readValue(responseBody, ChatGptResponse.class);
		return formatResponseCheck(jsonObject.getChoicesList().get(0).getMessage().getContent());

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
		chatGptRequest.setModel(ChatGptModels.CHAT_REQUEST_COMPLETIONS_TURBO);
		String requestBody = mapper.writeValueAsString(chatGptRequest);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		ChatGptResponse gptResponse = mapper
				.readValue(EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8), ChatGptResponse.class);
		String bookingId = (String) gptResponse.getChoicesList().get(0).getMessage().getContent();
		Object boookingDetails = HttpHelperUtils.getBooking(GetBookingDetails.builder().bookingId(bookingId).build(),
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
		res = formatResponse(res);
		return res;

	}

	public boolean checkSimilarity(String question1, String question2, ChatGptRequest chatGptRequest) throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/chat/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String newContent = "Check if the bellow two questions are exactly same and having same IDs mentioned. "
				+ "Return true if they are same and will have a same answer : " + "Question 1: " + question1
				+ "Question 2: " + question2 + "." + " Just say true Or false without explanations";
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

	@Override
	public Object tripjackModelChat(TripJackModelChat tripjackModelChat, HttpServletRequest httpServletRequest)
			throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String question = tripjackModelChat.getPrompt();
		String newPrompt = "Just fetch only the bookingid (Characters limit - 20) from - " + question
				+ ". The response should only contain the bookingid without any irrevalent text.";
		tripjackModelChat.setPrompt(newPrompt);
		request.setEntity(new StringEntity(mapper.writeValueAsString(tripjackModelChat)));
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		TripJackModelResponse gptResponse = mapper.readValue(responseBody, TripJackModelResponse.class);
		String bookingId = gptResponse.getChoices().get(0).getText();
		bookingId = bookingId.replaceAll("\\r|\\n", "");
		Object boookingDetails = HttpHelperUtils.getBooking(GetBookingDetails.builder().bookingId(bookingId).build(),
				httpServletRequest);
		String bookingDetail = mapper.writeValueAsString(boookingDetails);

		return generateResponseFromText(tripjackModelChat, bookingDetail.substring(0, bookingDetail.length() / 3),
				question);
	}

	public Object generateResponseFromText(TripJackModelChat tripjackModelChat, String details, String question)
			throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		String newContent = "Here are the details of the booking in json format - " + details + "." + question
				+ ".Only Answer the question and dont add any irrevalent information like gttp status or code.";
		tripjackModelChat.setPrompt(newContent);
		String requestBody = mapper.writeValueAsString(tripjackModelChat);
		request.setEntity(new StringEntity(requestBody, StandardCharsets.UTF_8));
		HttpResponse response = client.execute(request);
		HttpEntity entity = response.getEntity();
		String responseBody = EntityUtils.toString(entity, StandardCharsets.UTF_8);
		TripJackModelResponse gptResponse = mapper.readValue(responseBody, TripJackModelResponse.class);
		String responseString = gptResponse.getChoices().get(0).getText();
		responseString = formatResponse(responseString);

		return responseString;

	}

	public Object tripjackModelChatGeneral(TripJackModelChat tripjackModelChat, HttpServletRequest httpServletRequest)
			throws Exception {

		HttpClient client = HttpClients.createDefault();
		HttpPost request = new HttpPost("https://api.openai.com/v1/completions");
		request.setHeader("Authorization", "Bearer " + apiKey);
		request.setHeader("Content-Type", "application/json");
		request.setEntity(new StringEntity(mapper.writeValueAsString(tripjackModelChat)));
		HttpResponse response = client.execute(request);
		String responseBody = EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
		TripJackModelResponse gptResponse = mapper.readValue(responseBody, TripJackModelResponse.class);
		return formatResponse(gptResponse.getChoices().get(0).getText());
	}

	public String formatResponse(Object res) {
		String response = (String)res;
		response = response.replaceAll("(\\n|\\r)", "");
		response = response.replaceAll("\\\\", "");
		return response;
	}
	
	public String formatResponseCheck(Object res) {
		String response = (String)res;
		response = response.replaceAll("(\\n|\\r)", ", ");
		response = response.replaceAll("\\\\", "");
		return response;
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
