package com.xin.aoc.controller.core;


import jakarta.servlet.http.HttpServletRequest;
import net.sf.jsqlparser.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String index(HttpServletRequest request) {
        return "index";
    }
    @GetMapping(value = "/hackathon")
    public String hackathon(HttpServletRequest request) {
        return "hackathon";
    }

    @GetMapping(value = "/faq")
    public String faq(HttpServletRequest request) {
        return "faq";
    }

    @GetMapping(value = "/prizes")
    public String prizes( HttpServletRequest request) {
        return "prizes";
    }

    @GetMapping(value = "/eligibility")
    public String eligibility(HttpServletRequest request) {
        return "eligibility";
    }

    @GetMapping(value = "/cca")
    public String cca(HttpServletRequest request) {
        return "CCA";
    }
    @GetMapping(value = "/resources")
    public String resources( HttpServletRequest request) {
        return "resources";
    }

    @GetMapping(value = "/team")
    public String team(HttpServletRequest request) {
        return "team";
    }




}
