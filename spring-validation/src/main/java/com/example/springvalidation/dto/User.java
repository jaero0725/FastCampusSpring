package com.example.springvalidation.dto;

import com.example.springvalidation.annotation.YearMonth;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Valid
public class User {

    @NotBlank(message="필수값입니다.")
    private String name;

    @NotBlank(message="필수값입니다.")
    @Email(message = "이메일형식이 틀렸습니다.")
    private String email;

    private int age;

    //정규표현식으로 검증 @Pattern
    @NotBlank(message="필수값입니다.")
    @Pattern(regexp = "\\d{3}-\\d{3,4}-\\d{4}$", message = "xxx-xxxx-xxxx 형식으로 등록하세요.")
    private String phoneNumber;

    //추가
    @Size(min = 4, max = 6)
    @YearMonth
    private String reqYearMonth;

    @Valid      //붙여줘야한다.
    private List<Car> carList;

    //AssertTrue 메서드 명은 is로 시작해야 한다.
    //AssertTrue는 재활용이 불가능 하다. -> 똑같은 코드 중복이 많이 생김 => 직접 어노테이션을 만든다면 중복이 줄어 들 것이다.
    @AssertTrue(message = "yyyyMM형식에 맞추세요.")
    private boolean isReqYearMonthValidation(){ //메서드 명 : is ~
        System.out.println("assert true call");
        try {
            LocalDate localDate = LocalDate.parse( getReqYearMonth() + "01", DateTimeFormatter.ofPattern("yyyyMMdd"));
        } catch (Exception e){
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", reqYearMonth='" + reqYearMonth + '\'' +
                ", carList=" + carList +
                '}';
    }

    public List<Car> getCarList() {
        return carList;
    }

    public void setCarList(List<Car> carList) {
        this.carList = carList;
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

    public String getReqYearMonth() {
        return reqYearMonth;
    }

    public void setReqYearMonth(String reqYearMonth) {
        this.reqYearMonth = reqYearMonth;
    }
}
