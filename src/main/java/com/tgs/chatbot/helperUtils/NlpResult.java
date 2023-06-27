package com.tgs.chatbot.helperUtils;

import java.util.Map;

import lombok.Data;

@Data
public class NlpResult {
	
    private String intent;
    
    private Map<String, String> entities;

    public NlpResult(String intent, Map<String, String> entities) {
        this.intent = intent;
        this.entities = entities;
    }

    public String getIntent() {
        return intent;
    }


    public boolean hasEntity(String entityName) {
        return entities.containsKey(entityName);
    }

    public String getEntity(String entityName) {
        return entities.get(entityName);
    }
}







