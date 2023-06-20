package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
public class TravellerInfoWithPnr {
    private Map<String, String> pnrDetails;
}
