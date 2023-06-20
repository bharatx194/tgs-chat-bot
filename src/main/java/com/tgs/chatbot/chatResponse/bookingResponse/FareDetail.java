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
public class FareDetail {

	@JsonProperty("ADULT") private FareInfo ADULT;

    @JsonProperty("CHILD") private FareInfo CHILD;

    @JsonProperty("INFANT") private FareInfo INFANT;
}
