package com.xin.aoc.controller.community;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.*;
import com.xin.aoc.model.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Controller
public class CollectController {
    @Autowired
    private CollectMapper collectMapper;
    @Autowired
    private CampMapper campMapper;
    @Autowired
    private PostMapper postMapper;
    @Autowired
    private UserInfoMapper userInfoMapper;
    @PostMapping("/user/collect")
    public String collect(@RequestBody Map<String, Object> payload,
                       HttpServletRequest request, Model model) {
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        Integer value = (Integer) payload.get("value");
        int postId = Integer.parseInt((String) payload.get("postId"));

        Collect collect = new Collect();
        collect.setPostId(postId);
        collect.setUserId(user.getUserId());

        System.out.println("post: user/collect");

        if(value==0) {
            //uncollect
            collectMapper.uncollect(collect);
            System.out.println("post id: " +  postId + " uncollect");
        }else{
            //collect
            collectMapper.collect(collect);
            System.out.println("collecting: " +  postId +" collect");
        }
        return "redirect:/community";
    }


    @GetMapping(value="/user/collections")
    public String collections(@RequestParam(required = false, defaultValue = "1", value = "page")
                            Integer page,
                            @RequestParam(required = false,  value = "key") String key,
                            Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        session.setAttribute("login_user", user);
        if (page == null || page <= 0) page = 1;
        int size = 3;
        PageHelper.startPage(page, size);

        List<Integer> postIds = collectMapper.getPostIdsByUserId(user.getUserId());
        List<Post> posts = new ArrayList<Post>();
        for(Integer postId:postIds){
            posts.add(postMapper.getPostById(postId));
        }
        if(key!=null){
            System.out.println(key);
            posts = collectMapper.getPostByKeyPostIds(postIds);
        }

        for(Post p:posts){
            p.setUsername(userInfoMapper.getUserName(p.getUserId()));
            p.setUserImage(userInfoMapper.getUserImage(p.getUserId()));
            if(user!=null && collectMapper.checkExist(user.getUserId(),p.getPostId())!=0){
                p.setLiked(true);
            }else{
                p.setLiked(false);
            }
            if(user!=null && collectMapper.checkExist(user.getUserId(),p.getPostId())!=0){
                p.setCollected(true);
            }else{
                p.setCollected(false);
            }
        }

        PageInfo<Post> pageInfo = new PageInfo<Post>(posts, size);
        model.addAttribute("pageInfo", pageInfo);
        return "community/collections";
    }
}
