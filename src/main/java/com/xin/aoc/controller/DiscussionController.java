package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.DiscussionMapper;
import com.xin.aoc.mapper.ProblemMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Discussion;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;

@Controller
public class DiscussionController {
    @Autowired
    DiscussionMapper discussionMapper;
    @Autowired
    ProblemService problemService;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private CampMapper campMapper;

    @GetMapping(value="/camp/discussions")
    public String discuss(
            @RequestParam(required=false,value="id") int id,
            Model model, HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page){
        if (page == null || page <= 0) page = 1;
        int size = 8;
        PageHelper.startPage(page, size);

        Camp camp = campMapper.getCampById(id);
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        model.addAttribute("campInfo", camp);

        HttpSession session = request.getSession();
        session.setAttribute("login_user", user);

        List<Discussion> discussions = discussionMapper.getDiscussionById(camp.getCampId());


        PageInfo<Discussion> pageInfo = new PageInfo<Discussion>(discussions, size);
        model.addAttribute("pageInfo", pageInfo);

        return "user/discussions";
    }


    @PostMapping(value="/camp/discussions")
    public String post(
            @RequestParam(required=false,value="id",defaultValue="1") int campId,
            @RequestParam(required=false,value="content") String content,
            Model model, HttpServletRequest request,
            @RequestParam(required = false, defaultValue = "1", value = "page") Integer page){
        if (page == null || page <= 0) page = 1;
        int size = 8;
        PageHelper.startPage(page, size);


        Camp camp = campMapper.getCampById(campId);
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        model.addAttribute("problemInfo", camp);

        HttpSession session = request.getSession();
        session.setAttribute("login_user", user);

        String time=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
        Discussion discussion = new Discussion();
        discussion.setCurDate(time);
        discussion.setUserId(user.getUserId());
        discussion.setNickName(user.getNickName());
        discussion.setImage(user.getImage());
        discussion.setCampId(camp.getCampId());
        discussion.setContent(content);
        discussionMapper.addDiscussion(discussion);
        return "redirect:/camp/discussions?id="+camp.getCampId();
    }

    @RequestMapping(value="/camp/discussions/del")
    public String post( @RequestParam(required=false,value="id") int campId,
                        @RequestParam(required=false,value="cur_date") String curDate,
                        @RequestParam(required=false,value="user_id") int userId,
                        HttpServletRequest request,
                        Model model
                        ){

        Camp camp = campMapper.getCampById(campId);
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        model.addAttribute("campInfo", camp);

        HttpSession session = request.getSession();
        session.setAttribute("login_user", user);
        discussionMapper.delDiscussion(campId,  userId,  curDate);

        return "redirect:/problems/discussions?id="+camp.getCampId();
    }
}
