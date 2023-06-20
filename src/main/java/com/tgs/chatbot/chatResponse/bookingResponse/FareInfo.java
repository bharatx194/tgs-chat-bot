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
public class FareInfo {
    @JsonProperty("fC") private FareComponent fC;

    @JsonProperty("afC") private AFC afC;

    @JsonProperty("sR") private Long sR;

    @JsonProperty("bI") private BaggageInfo bI;
    
    @JsonProperty("isHB") private boolean isHB;
    @JsonProperty("rT") private Long rT;

    @JsonProperty("cc") private String cc;

    @JsonProperty("cB") private String cB;

    @JsonProperty("fB") private String fB;

    @JsonProperty("rB")
    private int rB;

    @JsonProperty("mi")
    private boolean mi;
}
