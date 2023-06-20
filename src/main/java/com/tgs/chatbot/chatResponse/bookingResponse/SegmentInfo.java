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
public class SegmentInfo {

	private Long id;
	
	@JsonProperty("fD") private FlightDesignator fD;
	
	private Long stops;
	
	private Long duration;
	
	@JsonProperty("da") private AirportInfo da;
	
	@JsonProperty("aa") private AirportInfo aa;
	
	@JsonProperty("dt") private String departureTime;
	
	@JsonProperty("at") private String arrivalTime;
	
    private boolean iand;
    
    private boolean isRs;
    
    private Long sN;
    
    @JsonProperty("ssrInfo") private SsrInfo ssrInfo;

}
