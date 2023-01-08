package com.xin.aoc.service.impl;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.mapper.AdminMapper;
import com.xin.aoc.mapper.UserInfoMapper;
import com.xin.aoc.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    public void addProblem(ProblemForm problem){
        System.out.println(problem.getCur_date()+ "!"+problem.getProblem());
        adminMapper.insert(problem);
    }
}
