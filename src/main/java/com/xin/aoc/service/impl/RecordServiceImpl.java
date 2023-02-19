package com.xin.aoc.service.impl;


import com.xin.aoc.mapper.RecordMapper;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

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
    public ArrayList<Integer> findSolved(UserInfo user) {

        int id = user.getUserId();
        return recordmapper.findSolved(id);
    }
}
