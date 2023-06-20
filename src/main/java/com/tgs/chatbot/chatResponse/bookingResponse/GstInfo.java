package com.tgs.chatbot.chatResponse.bookingResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GstInfo {
    private String gstNumber;
    private String email;
    private String registeredName;
    private String mobile;
    private String address;

}