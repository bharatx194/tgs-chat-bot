package com.tgs.chatbot.helperUtils;

import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

public class TestBot {
	
    private static StanfordCoreNLP pipeline;
    
    private static final String BOOKING_ID_PATTERN = "^[A-Z]{3}[0-9]{12}$";

    public static void main(String[] args) {

        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner, parse, sentiment");
        pipeline = new StanfordCoreNLP(props);
        String userQuery1 = "What is the status of bookingid TGS100330068668?";
        String response1 = processQuery(userQuery1);

        System.out.println("Response 1: " + response1);
        
        
    }
    public static boolean isBookingId(String text) {
        Pattern pattern = Pattern.compile(BOOKING_ID_PATTERN);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }
    
    private static String processQuery(String query) {
    	
        CoreDocument document = new CoreDocument(query);
        
        pipeline.annotate(document);

        for (CoreLabel token : document.tokens()) {
        	if(isBookingId(token.word())) return token.word();
        }
		return "";
    }

}

