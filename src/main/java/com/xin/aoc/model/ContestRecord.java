package com.xin.aoc.model;

import lombok.Data;

@Data
public class ContestRecord {
    private int contestRecordId;
    private int contestId;
    private int userId;
    private String problemId;
    private String userName;
    private String image;
    private String start;
    private String stop;
    private int score;

}
