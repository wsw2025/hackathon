package com.xin.aoc.form;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class CampForm {
    @Length(min = 2, max = 50, message = "title length must be 2-50 characters")
    private String title;

    @Length(min = 10, message = "title length must be 10 characters long")
    private String content;


    private String contact;

    private String location;

    private String campDate;

    @Length(min = 1, max = 10, message = "category length must be 1-10 characters")
    private String category;

    private String time;
}
