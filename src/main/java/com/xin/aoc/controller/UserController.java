package com.xin.aoc.controller;

import com.xin.aoc.form.UserForm;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.UserInfoService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class UserController {
    private Logger logger = LoggerFactory.getLogger(getClass());

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
            request.setAttribute("msg", "incorrect username or password");
        }
        return "login";
    }


    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/";
    }

    @GetMapping(value="/register")
    public String register(@ModelAttribute("obj") UserForm user) {
        return "register";
    }

    @PostMapping(value="/register")
    public String register(@ModelAttribute("obj") @Validated UserForm user, BindingResult rs) {
        logger.error("user:" + user.toString() + "," + rs.hasErrors());
        if (rs.hasErrors()) {
            logger.error("ERROR:" + user.toString());
            return "register";
        }
        return "register";
    }

    @RequestMapping(value="/get_check_code")
    @ResponseBody
    public String get_check_code(HttpServletRequest request) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer code = new StringBuffer();
        for (int i = 0; i < 6; i++) {
            int number = random.nextInt(62);
            code.append(str.charAt(number));
        }
        logger.info("checkCode", code.toString());
        request.getSession().setAttribute("check_code", code.toString());
        return "ok";
    }
}
