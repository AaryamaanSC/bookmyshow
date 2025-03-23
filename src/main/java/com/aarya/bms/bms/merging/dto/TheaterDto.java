package com.aarya.bms.bms.merging.dto;

import lombok.Data;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class TheaterDto {
    private String id;
    private String name;
    private List<String> screenIdList;
    private String cityId;

    public TheaterDto(String name, String cityId) {
        this.name = name;
        this.cityId = cityId;
    }
}
