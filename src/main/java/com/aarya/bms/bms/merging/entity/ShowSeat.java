package com.aarya.bms.bms.merging.entity;


import com.aarya.bms.bms.merging.enums.SeatState;
import com.aarya.bms.bms.merging.enums.SeatType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ShowSeat {
    @Id
    private String id;
    private   int row;
    private int col;
    private String showId;
    private SeatState state;
    private SeatType seatType;
    private double price;
}
