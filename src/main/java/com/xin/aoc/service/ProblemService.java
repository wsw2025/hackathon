package com.xin.aoc.service;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.model.Problem;

import java.util.List;

public interface ProblemService {
    Problem getProblem(String id);
    List<Problem> getProblems();

    void addScore(int id);
}
