package com.aarya.bms.bms.merging.dto;

import com.aarya.bms.bms.merging.enums.SeatType;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

@Data
public class ShowDto {
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieId;
    private String theatreId;
    private String screenId;
    private String screenSeatId;
    private List<String> showSeatIdList;
    private HashMap<SeatType,Double> seatTypeAndPrice;

    public ShowDto(LocalDateTime startTime, LocalDateTime endTime, String movieId, String theatreId, String screenId, String screenSeatId, HashMap<SeatType, Double> seatTypeAndPrice) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.movieId = movieId;
        this.theatreId = theatreId;
        this.screenId = screenId;
        this.screenSeatId = screenSeatId;
        this.seatTypeAndPrice = seatTypeAndPrice;
    }
}
