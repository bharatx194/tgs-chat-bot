package com.tgs.chatbot.chatResponse.bookingResponse;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RetrieveResponse {
    private Order order;
    private ItemInfos itemInfos;
    private GstInfo gstInfo;
    private Status status;

}
