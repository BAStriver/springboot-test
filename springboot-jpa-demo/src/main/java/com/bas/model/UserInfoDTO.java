package com.bas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class UserInfoDTO {

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "gender")
    private String gender;

    @JsonProperty(value = "age")
    private int age;
}
