package com.xin.aoc.service;

import com.xin.aoc.model.UserInfo;

import java.util.ArrayList;

public interface RecordService {
    int exist(int userId, int problemId);

    ArrayList<Integer> findSolved(UserInfo user);

    void addRecord(String curDate, int userId,int problemId);
}
