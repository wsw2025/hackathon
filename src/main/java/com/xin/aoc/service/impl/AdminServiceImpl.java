package com.xin.aoc.service.impl;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.mapper.AdminMapper;
import com.xin.aoc.mapper.UserInfoMapper;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService {
    static Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private AdminMapper adminMapper;
    @Override
    public void addProblem(ProblemForm problem){
        System.out.println(problem.getTitle()+ "!"+problem.getProblem());
        adminMapper.insert(problem);
    }

    public boolean changeAllProblemInfo(Problem problem) {
        try {
            adminMapper.changeAll(problem);
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return false;
    }

    public boolean delById(int id){
        try {
            adminMapper.delById(id);
            return true;
        } catch (Exception e) {
            logger.error(e.toString(), e);
        }
        return false;
    }


}
