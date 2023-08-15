package com.xin.aoc.controller.community;

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

    @PostMapping("/user/like")
    public String like(@RequestBody Map<String, Object> payload,
                           HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int postId = Integer.parseInt((String) payload.get("postId"));
        if(user==null)  return "redirect:/community";

        Like like = new Like();
        like.setPostId(postId);
        like.setUserId(user.getUserId());

        if(value==0) {
            //unlike
            likeMapper.unlike(like);
            System.out.println("post id: " +  postId);
            likeMapper.minusLikeCount(postId);
        }else{
            //like
            likeMapper.like(like);
            likeMapper.addLikeCount(postId);
        }
        return "redirect:/community";
    }
}
