package com.example.springexception.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
@Valid
public class User {

    @NotEmpty
    @Size(min = 1, max = 10)
    private String name;

    @NotNull
    @Min(1)
    private int age;
}