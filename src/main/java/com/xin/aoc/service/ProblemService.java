package com.xin.aoc.service;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.model.Problem;

import java.util.List;

public interface ProblemService {
    Problem getProblem(int id);
    Problem getProblemByName(String name);
    List<Problem> getProblems();

    List<Problem> getProblemsByKey(String key);

    List<Problem> getProblemsByContest(int id);

    void addScore(int id);

    String getCurNickName(int userId);
}
