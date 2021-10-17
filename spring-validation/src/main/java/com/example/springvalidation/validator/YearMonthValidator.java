package com.example.springvalidation.validator;

import com.example.springvalidation.annotation.YearMonth;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

//예를 통해 검사가 이뤄진다.
public class YearMonthValidator implements ConstraintValidator<YearMonth, String> {

    private String pattern;

    @Override
    public void initialize(YearMonth constraintAnnotation) {
        this.pattern = constraintAnnotation.pattern();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // yyyyMM
        System.out.println("isValid ");
        try {
            LocalDate localDate = LocalDate.parse( value + "01", DateTimeFormatter.ofPattern(this.pattern));
        } catch (Exception e){
            return false;
        }
        return true;

    }
}
