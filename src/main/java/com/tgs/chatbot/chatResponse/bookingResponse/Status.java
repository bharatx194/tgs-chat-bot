package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
    public boolean success;
    public int httpStatus;
}
