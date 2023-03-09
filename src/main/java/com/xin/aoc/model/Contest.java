package com.xin.aoc.model;

import lombok.Data;

@Data
public class Contest {
    private int contestId;
    private String start;
    private double duration;
    private String stop;
    private String title;
    private String content;
}
