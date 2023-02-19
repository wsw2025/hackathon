package com.xin.aoc.model;

import lombok.Data;

@Data
public class Submission {
    private int userId;
    private int problemId ;
    private String code;
    private String curDate;
    private String title;
    private int status;
}
