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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Controller
public class IndexController {
    @Autowired
    private CampMapper campMapper;
    @Autowired
    private RecordService recordService;

    @RequestMapping(value = "/")
    public String search(@RequestParam(required = false, defaultValue = "1", value = "page")
                         Integer page,
                         @RequestParam(required = false,  value = "key") String key,
                         Model model, HttpServletRequest request) {
        if (page == null || page <= 0) page = 1;
        int size = 8;
        PageHelper.startPage(page, size);

        List<Camp> camps = campMapper.getCamps();
        if(key!=null){
             System.out.println(key);
             camps = campMapper.getCampsByKey(key);
        }

        for(Camp c: camps){
            c.setUnrating(10-c.getRating());
            System.out.print(c.getTitle()+" ");
            System.out.println(c.getUnrating());
        }

        PageInfo<Camp> pageInfo = new PageInfo<Camp>(camps, size);

        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        HttpSession session = request.getSession();

        if(user!=null){
            session.setAttribute("login_user", user);
        }

        model.addAttribute("pageInfo", pageInfo);
        return "index";
    }
}
