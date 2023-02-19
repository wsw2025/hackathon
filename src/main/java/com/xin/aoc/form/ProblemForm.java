package com.xin.aoc.form;

import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.NumberFormat;

@Data
public class ProblemForm {
    @Length(min = 2, max = 50, message = "title length must be 2-50 characters long")
    private String title;

    @Length(min = 2, message = "problem length must be at least 2 characters long")
    private String problem;

    @Length(min = 1, max = 50, message = "answer length must be 1-50 characters long")
    private String answer;

    @Length(min = 1, max = 10000, message = "input length must be 1-1000 characters long")
    private String input;

    @Length(min = 1, max = 10000, message = "category length must be 1-1000 characters long")
    private String category;


    @Length(min = 1, max = 10000, message = "difficulty length must be 1-1000 characters long")
    private String difficulty;






}
