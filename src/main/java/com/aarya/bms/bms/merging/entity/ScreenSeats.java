package com.aarya.bms.bms.merging.entity;

import com.aarya.bms.bms.merging.enums.SeatType;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "screen-seats")
public class ScreenSeats {
    @Id
    private String id;
    private int totalRows;
    private int totalCols;
    private HashMap<SeatType,Integer> seatTypeAndCount;
}
