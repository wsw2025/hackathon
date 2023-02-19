package com.xin.aoc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

@SpringBootApplication
@MapperScan("com.xin.aoc.mapper")
public class WebApp {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(WebApp.class, args);
        ThymeleafViewResolver resolver = (ThymeleafViewResolver) context.getBean("thymeleafViewResolver");
        resolver.setRedirectHttp10Compatible(false);
    }
}