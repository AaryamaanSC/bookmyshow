package com.aarya.bms.bms.merging.entity;

import com.aarya.bms.bms.merging.enums.SeatType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Show {
    @Id
    private String id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String movieId;
    private String theatreId;
    private String screenId;
    private String screenSeatId;
    private List<String> showSeatIdList;
    private HashMap<SeatType,Double> seatTypeAndPrice;

}
