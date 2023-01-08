package com.xin.aoc.service.impl;


import com.xin.aoc.mapper.RecordMapper;

import com.xin.aoc.service.RecordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecordServiceImpl implements RecordService{
    @Autowired
    private RecordMapper recordmapper;

    @Override
    public int exist(int userId,int problemId){
        return recordmapper.checkExist(userId,problemId);
    }
    public void addRecord(String curDate, int userId,int problemId) {
        recordmapper.addRecord(curDate,userId,problemId);
    }
}
