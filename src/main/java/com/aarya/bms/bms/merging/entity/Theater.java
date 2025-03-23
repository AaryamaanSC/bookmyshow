package com.aarya.bms.bms.merging.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "theatre")
public class Theater {
    @Id
    private String id;
    private String name;
    private List<String> screenIdList;
    private String cityId;
}