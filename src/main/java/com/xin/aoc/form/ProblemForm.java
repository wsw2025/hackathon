package com.xin.aoc.form;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ProblemForm {
    @Pattern(regexp = "^\\d{4}\\-(0?[1-9]|1[012])\\-(0?[1-9]|[12][0-9]|3[01])$",message= "date format not valid")
    private String curDate;

    @Length(min = 2, max = 1000000, message = "problem length must be 2-1000000 characters long")
    private String problem;

    @Length(min = 1, max = 50, message = "answer length must be 1-50 characters long")
    private String answer;




}
