package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.model.Problem;
import com.xin.aoc.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    private ProblemService problemService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String list(@RequestParam(required = false, defaultValue = "1", value = "page")
                       Integer page, Model model) {
        if (page == null || page <= 0) page = 1;
        int size = 8;
        PageHelper.startPage(page, size);
        List<Problem> problems = problemService.getProblems();
        PageInfo<Problem> pageInfo = new PageInfo<Problem>(problems, size);
        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}
