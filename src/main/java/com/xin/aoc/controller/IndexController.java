package com.xin.aoc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(@RequestParam(required = false, value="name") String name,
                        Model model) {
        model.addAttribute("name", name);
        return "index";
    }
}

