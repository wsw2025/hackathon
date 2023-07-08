package com.xin.aoc.controller;

import com.xin.aoc.form.CampForm;
import com.xin.aoc.form.ProblemForm;
import com.xin.aoc.form.UserForm;
import com.xin.aoc.mapper.CampMapper;
import com.xin.aoc.model.Camp;
import com.xin.aoc.model.Problem;
import com.xin.aoc.model.UserInfo;
import com.xin.aoc.service.AdminService;
import com.xin.aoc.service.ProblemService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Controller
public class AdminController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private AdminService adminService;
    @Autowired
    private ProblemService problemService;
    @Autowired
    private CampMapper campMapper;



    @GetMapping(value="/admin/upload")
    public String upload(@ModelAttribute("CampForm") CampForm camp){
        return "admin/upload";
    }
    @PostMapping(value="/admin/upload")
    public String add(@ModelAttribute("CampForm") @Validated CampForm camp,
                      BindingResult rs){

        if (camp.getContent() != null && camp.getTitle()  != null) {
            if (rs.hasErrors()) {
                for (ObjectError error : rs.getAllErrors()) {
                    System.out.println(error.getDefaultMessage());
                }
                return "admin/upload";
            }

            campMapper.addCamp(camp);
            return "redirect:/";
        }
        return "admin/upload";
    }


    @GetMapping("/admin/edit")
    public String edit(@ModelAttribute("CampForm") CampForm campForm,
                       @RequestParam(required=false,value="id") int id,
                       Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");

        Camp camp = campMapper.getCampById(id);

        campForm.setTitle(camp.getTitle());
        campForm.setContent(camp.getContent());
        campForm.setCategory(camp.getCategory());
        campForm.setLocation(camp.getLocation());
        campForm.setCampDate(camp.getContact());
        campForm.setContact(camp.getCategory());

        session.setAttribute("login_user", user);
        session.setAttribute("camp", camp);

        return "admin/edit";
    }

    @PostMapping("/admin/edit")
    public String post_edit(@ModelAttribute("CampForm") CampForm campForm,
                                   @RequestParam(required=false,value="id") int id,
                                   @Validated UserForm editUser,
                                   HttpServletRequest request,
                                   BindingResult rs,
                                   Model model) {
        if (rs.hasErrors()) {
            return "admin/edit";
        }

        UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
        HttpSession session = request.getSession();
        session.setAttribute("login_user", user);
        model.addAttribute("campId",id);
        Camp camp = new Camp();

        if (editUser!=null) {
            camp.setTitle(campForm.getTitle());
            camp.setContact(campForm.getContact());
            camp.setContent(campForm.getContent());
            camp.setCategory(campForm.getCategory());
            camp.setLocation(campForm.getLocation());
            camp.setCampDate(campForm.getCampDate());
            camp.setCampId(id);
        }
        if (campMapper.editCamp(camp)) {
            campForm.setTitle(camp.getTitle());
            campForm.setContact(campForm.getContact());
            campForm.setContent(campForm.getContent());
            campForm.setCategory(campForm.getCategory());
            campForm.setLocation(campForm.getLocation());
            campForm.setCampDate(campForm.getCampDate());
            session.setAttribute("camp", camp);
            model.addAttribute("msg", "reset success");
        } else {
            model.addAttribute("msg", "reset failed");
        }

        return "admin/edit";
    }



    @RequestMapping("/check_title")
    @ResponseBody
    public String checkUserNameR(@RequestParam(value = "title") String title, HttpServletRequest request) {
        logger.info(title+"!!title");
        Camp camp = campMapper.getCampsByTitle(title);
        if((camp != null)){
            logger.info("exist!");
            return "exist";
        }else{
            return "ok";
        }
    }
    @PostMapping("/admin/delete")
    public String del(@RequestParam(value = "id") int id, HttpServletRequest request, Model model) {

        if(campMapper.delById(id)){
            logger.info("success!");
            model.addAttribute("del", "delete successful");

        }else{
            model.addAttribute("del", "delete not successful");
        }
        return "redirect:/";
    }

}
