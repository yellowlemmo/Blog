package com.cui.blog.demo.utils;

import com.cui.blog.demo.config.MyUserDetails;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpSession;

/**
 * spring security工具类
 */
public class SpringSecurityUtil {

    //session 由controller 注入参数传入 获取当前登录用户名
    public static MyUserDetails currentUser(HttpSession session) {
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return ((MyUserDetails)securityContext.getAuthentication().getPrincipal());
    }
}
