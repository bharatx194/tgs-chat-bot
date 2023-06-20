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
public class FareComponent {

    @JsonProperty("NCM") private Double NCM;
    @JsonProperty("TAF") private Double TAF;
    @JsonProperty("TF") private Double TF;
    @JsonProperty("NF") private Double NF;
    @JsonProperty("BF") private Double BF;
}
