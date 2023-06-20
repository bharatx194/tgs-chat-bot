package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightDesignator {

    @JsonProperty("aI") private AirlineInfo aI;
    @JsonProperty("fN") private String FlightNo;
    @JsonProperty("eT") private String AirlineType;
}