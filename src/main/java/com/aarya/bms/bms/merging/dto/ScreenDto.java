package com.aarya.bms.bms.merging.dto;

import lombok.Data;

import java.util.List;

@Data
public class ScreenDto {
    private String id ;
    private String name;
    private String screenSeatId;
    private List<String> showIdList;

    public ScreenDto(String name, String screenSeatId) {
        this.name = name;
        this.screenSeatId = screenSeatId;
    }
}
