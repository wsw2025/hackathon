package com.xin.aoc.config;

import com.xin.aoc.model.UserInfo;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class LoginInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor(false)).addPathPatterns("/user/**");
        registry.addInterceptor(new LoginInterceptor(true)).addPathPatterns("/admin/**");
    }

    class LoginInterceptor implements HandlerInterceptor {
        private Logger logger = LoggerFactory.getLogger(getClass());
        private boolean checkAdmin = false;

        public LoginInterceptor(boolean checkAdmin) {
            this.checkAdmin = checkAdmin;
        }
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                                 Object handler) throws Exception {
            logger.error("getServletPath:" + request.getServletPath());
            UserInfo user = (UserInfo)request.getSession().getAttribute("login_user");
            if (user == null) {
                request.getRequestDispatcher("/login").forward(request, response);
                return false;
            } else if (this.checkAdmin && user.getIsAdmin() == 0) {
                request.getRequestDispatcher("/").forward(request, response);
                return false;
            } else {
                return true;
            }
        }
    }
}
