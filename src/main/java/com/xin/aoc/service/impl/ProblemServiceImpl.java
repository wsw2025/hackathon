package com.xin.aoc.service.impl;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.mapper.ProblemMapper;
import com.xin.aoc.mapper.UserInfoMapper;
import com.xin.aoc.model.Problem;
import com.xin.aoc.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public Problem getProblem(int id) {
        return problemMapper.getProblemById(id);
    }

    public List<Problem> getProblems() {
        return problemMapper.getAllProblems();
    }

    public List<Problem> getProblemsByKey(String key) {
        return problemMapper.getAllProblemsByKey(key);
    }

    public List<Problem> getProblemsByContest(int id){
        return problemMapper.getProblemsByContest(id);
    }

    public String getCurNickName(int userId){
        return  userInfoMapper.getCurNickName(userId);
    }
    public void addScore(int id){
        problemMapper.addScoreById(id);
    }

    public Problem getProblemByName(String name){
        return problemMapper.getProblemByName(name);
    }
}
