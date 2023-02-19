package com.xin.aoc.service;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;

public interface AdminService {
    public void addProblem(ProblemForm problem);

    public boolean changeAllProblemInfo(Problem problem);

    public boolean delById(int id);
}
