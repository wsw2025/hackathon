package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.DiscussionMapper;
import com.xin.aoc.mapper.ProblemMapper;
import com.xin.aoc.model.Discussion;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class DiscussionController {
    @Autowired
    DiscussionMapper discussionMapper;
    @Autowired
    ProblemService problemService;

    @RequestMapping(value="/problems/discussions")
    public String discuss(
            @RequestParam(required=false,value="id") String id,
            Model model, HttpServletRequest request,
             @RequestParam(required = false, defaultValue = "1", value = "page") Integer page){
        if (page == null || page <= 0) page = 1;
        int size = 8;
        PageHelper.startPage(page, size);

        Problem problem = problemService.getProblem(id);
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        model.addAttribute("problemInfo", problem);

        List<Discussion> discussions = discussionMapper.getDiscussionById(problem.getProblemId());
        PageInfo<Discussion> pageInfo = new PageInfo<Discussion>(discussions, size);
        model.addAttribute("pageInfo", pageInfo);

        return "user/discussions";
    }
}
