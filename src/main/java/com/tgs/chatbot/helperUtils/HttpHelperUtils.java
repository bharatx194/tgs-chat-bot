package com.tgs.chatbot.helperUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tgs.chatbot.dbmodel.request.GetBookingDetails;

@SuppressWarnings({ "deprecation", "unused" })
public class HttpHelperUtils {

	protected static String apiKey = "31744955059537e-95d5-4111-991c-be6b91b7b35b";

	public static String postContent(HttpUrls url, String data, HttpServletRequest httpServletRequest) throws Exception {

		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost post = new HttpPost(url.getUrl());
		post.addHeader("Content-Type", "application/json");
		post.setEntity(new StringEntity(data, "application/json", "UTF-8"));
		if (httpServletRequest != null) {
			post.addHeader("Authorization", httpServletRequest.getHeader("Authorization"));
		} else
			post.addHeader("apikey", apiKey);
		HttpResponse response = httpClient.execute(post);
		String responseStr = null;
		if (response.getEntity() != null) {
			responseStr = readData(response.getEntity().getContent(), true);
		}
		return responseStr;

	}

	public static String readData(InputStream inputstream, boolean closeStream) throws IOException {
		InputStreamReader inputstreamreader = new InputStreamReader(inputstream, "UTF-8");
		BufferedReader bufferedreader = new BufferedReader(inputstreamreader);
		StringBuilder strBuff = new StringBuilder();
		String responseStr = null;
		while ((responseStr = bufferedreader.readLine()) != null) {
			strBuff.append(responseStr);
		}
		return strBuff.toString();
	}

	public static Object getBooking(GetBookingDetails getBookingDetails, HttpServletRequest httpServletRequest)
			throws Exception {
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		String data = mapper.writeValueAsString(getBookingDetails).toString();
		String response = HttpHelperUtils.postContent(HttpUrls.BOOKING_DETAILS,
				data, httpServletRequest);
		Object res = mapper.readValue(response, Object.class);
		return res;
	}

}
