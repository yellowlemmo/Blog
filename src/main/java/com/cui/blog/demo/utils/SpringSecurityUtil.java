package com.cui.blog.demo.utils;

import com.cui.blog.demo.pojo.User;
import org.springframework.security.core.context.SecurityContextImpl;

import javax.servlet.http.HttpSession;

/**
 * spring security工具类
 */
public class SpringSecurityUtil {

    //session 由controller 注入参数传入 获取当前登录用户名
    public static User currentUser(HttpSession session) {
        SecurityContextImpl securityContext = (SecurityContextImpl) session.getAttribute("SPRING_SECURITY_CONTEXT");
        return (User)securityContext.getAuthentication().getPrincipal();
    }
}
