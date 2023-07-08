package com.xin.aoc.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.RecordService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@Controller
public class RatingController {
    @Autowired
    private CampMapper campMapper;

    @PostMapping("/rate")
    @ResponseBody
    public String rateCamp(@RequestBody Map<String, Object> payload, HttpServletRequest request) {
        String valueStr = (String) payload.get("value");
        int value = Integer.parseInt(valueStr);

        return "ok";
    }
}
