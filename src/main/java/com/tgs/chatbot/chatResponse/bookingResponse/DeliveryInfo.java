package com.tgs.chatbot.chatResponse.bookingResponse;
import java.io.Serializable;
import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DeliveryInfo implements Serializable {
	
    private static final long serialVersionUID = 1L;
    
	private List<String> emails;
	
    private List<String> contacts;

}