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
public class TaxAndFee {
    @JsonProperty("MF") private Double MF;
    @JsonProperty("OT") private Double OT;
    @JsonProperty("AGST") private Double AGST;
    @JsonProperty("MFT") private Double MFT;
    @JsonProperty("YQ") private Double YQ;
    @JsonProperty("YR") private Double YR;
}
