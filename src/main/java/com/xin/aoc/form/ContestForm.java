package com.xin.aoc.form;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ContestForm {
    @Length(min = 2, max = 50, message = "title length must be 2-50 characters long")
    private String title;

    @Length(min = 1, max = 10000, message = "content length must be 1-1000 characters long")
    private String content;

    private double duration;

    @Length(min = 16, max = 16, message = "start time must not be empty!")
    private String start;

    @Length(min = 16, max = 16, message = "stop time must not be empty!")
    private String stop;

    private int contestId;


}
