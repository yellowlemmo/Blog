package com.cui.blog.demo.Filter;

import com.cui.blog.demo.utils.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.security.sasl.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class VerifyCodeFilter extends GenericFilterBean {

    private String defaultFilterProcessUrl = "/login";
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        if("POST".equalsIgnoreCase(request.getMethod()) && defaultFilterProcessUrl.equals(request.getServletPath())){
            String requestCaptcha = request.getParameter("code");
            String genCaptche = (String)request.getSession().getAttribute("index_code");
            if(!StringUtils.isNotEmpty(requestCaptcha)) {
                throw new AuthenticationServiceException("验证码不能为空");
            }
            if(!genCaptche.toLowerCase().equals(requestCaptcha.toLowerCase())) {
                throw new AuthenticationServiceException("验证码错误");
            }
        }
        filterChain.doFilter(request,response);
    }
}
