package com.xin.aoc.service.impl;

import com.xin.aoc.mapper.ProblemMapper;
import com.xin.aoc.mapper.SubmissionMapper;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.Submission;
import com.xin.aoc.service.SubmissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubmissionServiceImpl implements SubmissionService {
    @Autowired
    private SubmissionMapper submissionMapper;

    public void update(int problemId, int userId, String code, String time, int status, String title){
        submissionMapper.updateCodeById( problemId,  userId,  code, time, status, title);
    }
    public List<Submission> getSubmissionsByUserId(int userId, int problemId){
        return submissionMapper.getSubmissionsByUserId(userId, problemId);
    }
    public Submission getSubmission(int userId, int problemId, String curDate){
        return submissionMapper.getSubmissionByUserId(userId, problemId,  curDate);

    }

}
