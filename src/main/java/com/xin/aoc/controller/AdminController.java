package com.xin.aoc.controller;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminService adminService;

    @GetMapping(value="/admin/upload")
    public String upload(@ModelAttribute("ProblemForm") ProblemForm problem){
        return "admin/upload";
    }
    @PostMapping(value="/admin/upload")
    public String add(@ModelAttribute("ProblemForm") @Validated ProblemForm problem, BindingResult rs){
        if (problem.getProblem() != null && problem.getCur_date()  != null) {
            if (rs.hasErrors()) {
                for (ObjectError error : rs.getAllErrors()) {
                    System.out.println(error.getDefaultMessage());
                }
                return "admin/upload";
            }

            adminService.addProblem(problem);
            return "redirect:/";
        }

        return "admin/upload";

    }

}
