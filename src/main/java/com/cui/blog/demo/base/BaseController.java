package com.cui.blog.demo.base;

import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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


    /**
     * 获取当前用户
     * @return
     */
    protected User getCurrrentUser(){
        return  SpringSecurityUtil.currentUser(session);
    }

    /**
     * 初始化请求和响应
     * @param request
     * @param response
     */
    public void setReqAndRes(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
    }
}
