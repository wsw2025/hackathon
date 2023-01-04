package com.xin.aoc.controller;

import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {
    @Autowired
    private UserInfoService userInfoService;

    @RequestMapping("/login")
    public String login(@RequestParam(required=false,value="username") String username,
                        @RequestParam(required=false,value="password") String password,
                        HttpServletRequest request) {
        if (username != null && password != null) {
            UserInfo userInfo = userInfoService.getUserInfo(username, password);
            if (userInfo != null) {
                userInfo.setPassword("");
                HttpSession session = request.getSession();
                session.setAttribute("login_user", userInfo);
                return "redirect:/";
            }
            request.setAttribute("msg", "username or password is error!");
        }
        return "login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @RequestMapping("/register")
    public String register(@RequestParam(required=false,value="username") String username,
                           @RequestParam(required=false,value="email") String email,
                           @RequestParam(required=false,value="password") String password,
                           @RequestParam(required=false,value="nickname") String nickName
                            HttpServletRequest request){
        if (username != null && email != null && password != null
            ) {
                userInfoService.addUserInfo(username, email, password,nickName);

            HttpSession session = request.getSession();
            session.setAttribute("login_user", userInfo);
            return "redirect:/";
        }
        return "/register";
    }
}
