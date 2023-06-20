package com.tgs.chatbot.chatResponse.bookingResponse;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Order {
    public String bookingId;
    public double amount;
    public double markup;
    public DeliveryInfo deliveryInfo;
    public String status;
    public String createdOn;
}
