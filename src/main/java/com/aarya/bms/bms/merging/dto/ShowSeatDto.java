package com.aarya.bms.bms.merging.dto;

import com.aarya.bms.bms.merging.enums.SeatState;
import com.aarya.bms.bms.merging.enums.SeatType;
import lombok.Data;

@Data
public class ShowSeatDto {
    private String id;
    private   int row;
    private int col;
    private String showId;
    private SeatState state;
    private SeatType seatType;
    private double price;

    public ShowSeatDto(int row, int col, String showId, SeatState state, SeatType seatType, double price) {
        this.row = row;
        this.col = col;
        this.showId = showId;
        this.state = state;
        this.seatType = seatType;
        this.price = price;
    }
}
