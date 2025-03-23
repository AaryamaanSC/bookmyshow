package com.aarya.bms.bms.merging.dto;


import com.aarya.bms.bms.merging.enums.SeatType;
import lombok.Data;

import java.util.HashMap;

@Data
public class ScreenSeatsDto {
    private String id;
    private int totalRows;
    private int totalCols;
    private HashMap<SeatType,Integer> seatTypeAndCount;

    public ScreenSeatsDto(int totalRows, int totalCols, HashMap<SeatType, Integer> seatTypeAndCount) {
        this.totalRows = totalRows;
        this.totalCols = totalCols;
        this.seatTypeAndCount = seatTypeAndCount;
    }
}
