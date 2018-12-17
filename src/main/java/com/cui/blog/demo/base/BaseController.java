package com.cui.blog.demo.base;

import com.cui.blog.demo.config.MyUserDetails;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class BaseController {

    @Autowired
    protected HttpServletRequest request;

    protected HttpServletResponse response;

    @Autowired
    protected HttpSession session;

    protected MyUserDetails getCurrrentUser(){
        return  SpringSecurityUtil.currentUser(session);
    }

    public void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
}
