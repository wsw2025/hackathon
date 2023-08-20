package com.xin.aoc.controller.community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.CommentMapper;
import com.xin.aoc.mapper.LikeMapper;
import com.xin.aoc.mapper.RatingMapper;
import com.xin.aoc.model.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


@Controller
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CampMapper campMapper;

    @PostMapping("/user/comment")
    public String like(@RequestParam(required=false,value="id") int postId,
                       @RequestParam(required=false,value="content") String content,
                       HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        if(user==null)  return "redirect:/community";

        Comment comment = new Comment();
        comment.setPostId(postId);
        comment.setUserId(user.getUserId());
        comment.setContent(content);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
        String formattedDate = date.format(formatter);;
        comment.setPostDate(formattedDate);

        //insert to database
        commentMapper.insert(comment);
        System.out.println(comment.getContent());
        model.addAttribute("posted","Comment posted!");
        return "redirect:/community";
    }


}
