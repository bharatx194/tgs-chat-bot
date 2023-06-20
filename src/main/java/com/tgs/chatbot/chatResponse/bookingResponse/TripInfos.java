package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TripInfos {

    @JsonProperty("sI") private List<SegmentInfo> sI;

    @JsonProperty("totalPriceList") private List<TotalPriceList> totalPriceList;

}
