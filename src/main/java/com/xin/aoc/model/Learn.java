package com.xin.aoc.model;

import lombok.Data;

@Data
public class Learn {
    private int learnId;
    private String curDate;
    private int userId;
    private String nickName;
    private String image;
    private String content;
    private String title;
    private int status;
    private String difficulty;
    private String category;
}
