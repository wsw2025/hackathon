package com.xin.aoc.controller.core;

import com.xin.aoc.mapper.PostMapper;
import com.xin.aoc.mapper.SubscribeMapper;
import com.xin.aoc.model.Discussion;
import com.xin.aoc.model.Post;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class StoriesController {
    @Autowired
    PostMapper postMapper;
    @GetMapping(value = "/gallery")
    public String gallery(Model model, HttpServletRequest request) {
        return "gallery";
    }

    @GetMapping(value = "/photography")
    public String photo(Model model, HttpServletRequest request) {
        List<Post> posts = postMapper.getPosts();
        model.addAttribute("posts", posts);
        return "photography";
    }

    @GetMapping(value = "/art")
    public String art(Model model, HttpServletRequest request) {
        return "art";
    }

    @GetMapping(value = "/coding")
    public String coding(Model model, HttpServletRequest request) {
        return "coding";
    }
}
