package com.example.springvalidation.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@Valid
public class Car {

    @NotBlank
    private String name;

    @JsonProperty("car_number")
    @NotBlank
    private String car_number;

    @JsonProperty("TYPE")
    @NotBlank
    private String TYPE;

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", car_number='" + car_number + '\'' +
                ", TYPE='" + TYPE + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar_number() {
        return car_number;
    }

    public void setCar_number(String car_number) {
        this.car_number = car_number;
    }

    public String getTYPE() {
        return TYPE;
    }

    public void setTYPE(String TYPE) {
        this.TYPE = TYPE;
    }
}
