package com.xin.aoc.service;

public interface RecordService {
    int exist(int userId, int problemId);

    void addRecord(String curDate, int userId,int problemId);
}
