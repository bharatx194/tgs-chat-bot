package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class AIR {
    public List<TripInfos> tripInfos;
    public List<TravellerInfoWithPnr> travellerInfos;
    public TotalPriceInfo totalPriceInfo;
}
