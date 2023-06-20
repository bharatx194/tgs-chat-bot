package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AirportInfo {
    @JsonProperty("code") private String code;
    @JsonProperty("name") private String name;
    @JsonProperty("cityCode") private String cityCode;
    @JsonProperty("city") private String city;
    @JsonProperty("country") private String country;
    @JsonProperty("countryCode") private String countryCode;
    @JsonProperty("terminal") private String terminal;
}
