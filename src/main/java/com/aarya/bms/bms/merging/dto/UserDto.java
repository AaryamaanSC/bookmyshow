package com.aarya.bms.bms.merging.dto;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private String id;
    private String name;
    private String email;
    private List<String> bookingIds;

    public UserDto(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
