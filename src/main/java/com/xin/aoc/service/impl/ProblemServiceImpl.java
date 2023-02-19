package com.xin.aoc.service.impl;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.mapper.ProblemMapper;
import com.xin.aoc.model.Problem;
import com.xin.aoc.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {
    @Autowired
    private ProblemMapper problemMapper;

    @Override
    public Problem getProblem(String id) {
        return problemMapper.getProblemById(id);
    }

    public List<Problem> getProblems() {
        return problemMapper.getAllProblems();
    }

    public List<Problem> getProblemsByKey(String key) {
        return problemMapper.getAllProblemsByKey(key);
    }


    public void addScore(int id){
        problemMapper.addScoreById(id);
    }
}
