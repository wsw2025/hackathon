package com.xin.aoc.controller;

import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.AdminService;
import com.xin.aoc.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProblemService problemService;

    @GetMapping(value="/admin/upload")
    public String upload(@ModelAttribute("ProblemForm") ProblemForm problem){
        return "admin/upload";
    }
    @PostMapping(value="/admin/upload")
    public String add(@ModelAttribute("ProblemForm") @Validated ProblemForm problem, BindingResult rs){
        if (problem.getProblem() != null && problem.getTitle()  != null) {
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

    @GetMapping("/admin/edit")
    public String edit(@ModelAttribute("ProblemForm") ProblemForm problemForm,
                       @RequestParam(required=false,value="id") String id,
                       Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");

        Problem problem = problemService.getProblem(id);

        problemForm.setTitle(problem.getTitle());
        problemForm.setProblem(problem.getProblem());
        problemForm.setAnswer(problem.getAnswer());
        problemForm.setCategory(problem.getCategory());
        problemForm.setDifficulty(problem.getDifficulty());
        problemForm.setInput(problem.getInput());

        session.setAttribute("login_user", user);
        session.setAttribute("problem", problem);

        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String handleFileUpload(@ModelAttribute("ProblemForm") ProblemForm problemInfo,
                                   @RequestParam(required=false,value="id") String id,
                                   @Validated UserForm editUser,
                                   HttpServletRequest request,
                                   BindingResult rs,
                                   Model model) {
        if (rs.hasErrors()) {
            return "admin/edit";
        }

        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        HttpSession session = request.getSession();
        session.setAttribute("login_user", user);

        Problem problem = problemService.getProblem(id);
        model.addAttribute("problemId",id);
        Problem newProblem = new Problem();

        if (editUser!=null) {
            newProblem.setTitle(problemInfo.getTitle());
            newProblem.setProblem(problemInfo.getProblem());
            newProblem.setAnswer(problemInfo.getAnswer());
            newProblem.setCategory(problemInfo.getCategory());
            newProblem.setDifficulty(problemInfo.getDifficulty());
            newProblem.setInput(problemInfo.getInput());
            newProblem.setProblemId(Integer. parseInt(id));
        }
        logger.info("newuser "+newProblem.getInput());
        if (adminService.changeAllProblemInfo(newProblem)) {
            problemInfo.setTitle(newProblem.getTitle());
            problemInfo.setProblem(newProblem.getProblem());
            problemInfo.setAnswer(newProblem.getAnswer());
            problemInfo.setCategory(newProblem.getCategory());
            problemInfo.setDifficulty(newProblem.getDifficulty());
            session.setAttribute("problem", newProblem);
            model.addAttribute("msg", "reset success");
        } else {
            model.addAttribute("msg", "reset failed");
        }

        return "admin/edit";
    }


    @PostMapping("/admin/delete")
    public String del(@RequestParam(value = "id") int problemId, HttpServletRequest request, Model model) {

        if(adminService.delById(problemId)){
            logger.info("success!");
            model.addAttribute("del", "delete successful");

        }else{
            model.addAttribute("del", "delete not successful");
        }
        return "redirect:/";
    }

}
