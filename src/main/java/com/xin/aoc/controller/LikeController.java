package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.mapper.LikeMapper;
import com.xin.aoc.mapper.RatingMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Like;
import com.xin.aoc.model.Rating;
import com.xin.aoc.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.Map;


@Controller
public class LikeController {
    @Autowired
    private LikeMapper likeMapper;
    @Autowired
    private CampMapper campMapper;

    @PostMapping("/like")
    public String like(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int discussionId = Integer.parseInt((String) payload.get("discussionId"));
        int campId = (int) payload.get("campId");
        if(user==null)  return "redirect:/camp/discussions?id="+ campId;


        Like like = new Like();
        like.setDiscussionId(discussionId);
        like.setUserId(user.getUserId());

        if(value==0) {
            //unlike
            likeMapper.unlike(like);
            likeMapper.minusLikeCount(discussionId);
        }else{
            //like
            likeMapper.like(like);
            likeMapper.addLikeCount(discussionId);

        }
        return "redirect:/camp/discussions?id="+ campId;
    }
}
