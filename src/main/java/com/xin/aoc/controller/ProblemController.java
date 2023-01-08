package com.xin.aoc.controller;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.AdminService;
import com.xin.aoc.service.ProblemService;
import com.xin.aoc.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import net.sf.jsqlparser.expression.StringValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;

@Controller
public class ProblemController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProblemService problemService;
    @Autowired
    private IndexController indexController;
    @Autowired
    private RecordService recordService;



    @GetMapping(value="/problems")
    public String add(@RequestParam(required=false,value="id") String id, Model model){
        logger.info(id);
        Problem problem = problemService.getProblem(id);
        model.addAttribute("problemInfo", problem);
        return "problems";
    }

    @PostMapping(value="/problems")
    public String check(@RequestParam(required=false,value="id") String id,
                        @RequestParam(required=false,value="answer") String answer,
                        Model model, HttpServletRequest request){

        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Problem problem = problemService.getProblem(id);
        model.addAttribute("problemInfo", problem);
        String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        if(user==null)  return "redirect:user/submission";
        if(answer.equals(problem.getAnswer()) && user!=null){
            request.setAttribute("msg", "Your answer is correct!");
            if(recordService.exist(user.getUserId(),problem.getProblemId())<1) {
                problemService.addScore(user.getUserId());
                recordService.addRecord(time, user.getUserId(),problem.getProblemId());
            }
        }else{
            request.setAttribute("msg", "Your answer is incorrect, please try again.");
        }
        return "user/submission";
    }

}