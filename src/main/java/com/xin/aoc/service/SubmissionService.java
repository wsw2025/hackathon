package com.xin.aoc.service;

import com.xin.aoc.model.Problem;
import com.xin.aoc.model.Submission;

import java.util.List;

public interface SubmissionService {
         void update(int problemId, int userId, String code, String time, int status, String title);

        List<Submission> getSubmissionsByUserId(int usrId, int problemId);

      Submission getSubmission( int userId, int problemId, String curDate);
}
