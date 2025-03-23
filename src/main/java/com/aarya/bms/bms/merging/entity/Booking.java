package com.aarya.bms.bms.merging.entity;

import com.aarya.bms.bms.merging.enums.BookingStatus;
import com.aarya.bms.bms.merging.strategy.PaymentStrategy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class Booking {
    @Id
    private String id;
    private String userId;
    private String showId;
    private List<String> seatIdsToBeBooked;
    private double totalPrice;
    private BookingStatus bookingStatus;
    private PaymentStrategy paymentStrategy;
}
