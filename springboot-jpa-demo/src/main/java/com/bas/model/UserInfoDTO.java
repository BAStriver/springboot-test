package com.bas.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.util.Date;

@Data
public class UserInfoDTO {

    @NotBlank(message = "name can not null")
    @Length(max = 20, message = "name can not exceed 20 characters")
    @Pattern(regexp = "^[\\u4E00-\\u9FA5A-Za-z0-9\\*]*$", message = "no more than 10 characters，including text, letters, and numbers.")
    @JsonProperty(value = "name")
    private String name;

    @NotBlank(message = "gender can not null")
    @JsonProperty(value = "gender")
    private String gender;

    @NotNull(message = "age can not empty")
    @Min(value = 1, message = "age should be between 1 and 120")
    @Max(value = 120, message = "age should be between 1 and 120")
    @JsonProperty(value = "age")
    private int age;

    @NotNull(message = "futureDate can not empty")
    @Future(message = "futureDate is invalid")
    @JsonProperty(value = "futureDate")
    private Date futureDate;

    @NotNull(message = "birthDate can not empty")
    @Past(message = "birthDate is invalid")
    @JsonProperty(value = "birthDate")
    private Date birthDate;
}
