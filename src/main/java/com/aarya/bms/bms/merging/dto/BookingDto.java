package com.aarya.bms.bms.merging.dto;

import com.aarya.bms.bms.merging.enums.BookingStatus;
import com.aarya.bms.bms.merging.strategy.PaymentStrategy;
import lombok.Data;

import java.util.List;

@Data
public class BookingDto {
    private String id;
    private String userId;
    private String showId;
    private List<String> seatIdsToBeBooked;
    private double totalPrice;
    private BookingStatus bookingStatus;
    private PaymentStrategy paymentStrategy;

    public BookingDto(String userId, String showId, List<String> seatIdsToBeBooked, PaymentStrategy paymentStrategy) {
        this.userId = userId;
        this.showId = showId;
        this.seatIdsToBeBooked = seatIdsToBeBooked;
        this.paymentStrategy = paymentStrategy;
    }
}
