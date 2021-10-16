package com.example.springvalidation.dto;

import javax.validation.Valid;
import javax.validation.constraints.*;

@Valid
public class User {

    @NotBlank(message="필수값입니다.")
    private String name;

    @NotBlank(message="필수값입니다.")
    @Email(message = "이메일형식이 틀렸습니다.")
    private String email;

    @NotBlank(message="필수값입니다.")
    private int age;

    //정규표현식으로 검증 @Pattern
    @NotBlank(message="필수값입니다.")
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}$", message = "xxx-xxxx-xxxx 형식으로 등록하세요.")
    private String phoneNumber;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age='" + age + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
