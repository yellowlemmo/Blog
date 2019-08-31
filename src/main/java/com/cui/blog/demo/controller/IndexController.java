package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.Role;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.List;
import java.util.Random;

@Controller
public class IndexController extends BaseController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    /**
     * 用户角色service接口
     */
    @Autowired
    private RoleService roleService;

    /**
     * 用户service接口
     */
    @Autowired
    private UserService userService;

    /**
     * 博客service接口
     */
    @Autowired
    private ArticleService articleService;


    @RequestMapping("/index")
    public String index(Model model,
                        @RequestParam(value = "sort",defaultValue = "default") String sortType,
                        @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) throws Exception{
        Sort sort = new Sort(Sort.Direction.DESC,sortType);
        Page<Article> page = null;
        //sortType不为空默认分页
        if (sortType.equals("default")){
            page = articleService.findAllBlog(pageIndex,pageSize);
        }else {
            //根据sortType排序分页
            page = articleService.findAllBlogSortByKey(pageIndex,pageSize,sortType);
        }
        //点击量top5的博客
        List<Article> hostBlog = articleService.findTopFiveBolg();
        //最新发布的前5篇
        List<Article> newBlog = articleService.findNewFiveBlog();
        model.addAttribute("page",page);
        model.addAttribute("hostBlog",hostBlog);
        model.addAttribute("newBlog",newBlog);
        model.addAttribute("user",SpringSecurityUtil.currentUser(session));
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        try{
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");
        }catch (AuthenticationServiceException authenticationServiceException){
            System.out.println(authenticationServiceException.toString());
            authenticationServiceException.printStackTrace();
        }

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register(Model model){
        logger.info("初始化注册页面");
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("roles",roles);
        return "register";
    }

    @RequestMapping(value = "/login/error")
    public String loginError(Model model,HttpServletRequest request){
        try{
        logger.error("登录失败");
//        AuthenticationException exception = (AuthenticationException) request.getAttribute("SPRING_SECURITY_LAST_EXCEPTION");
//        System.out.println(exception);
        model.addAttribute("errorMessage","用户名密码不正确！");
        }catch (AuthenticationServiceException authenticationServiceException){
            System.out.println(authenticationServiceException.toString());
            authenticationServiceException.printStackTrace();
        }

        return "login";
    }


    /**
     * 生成验证码
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping(value = "/code")
    public void code(HttpServletRequest request,HttpServletResponse response) throws Exception{
        VerifyCodeUtil vc = new VerifyCodeUtil();
        BufferedImage image = vc.getImage();
        String text = vc.getText();
        HttpSession session = request.getSession();
        session.setAttribute("index_code",text);
        VerifyCodeUtil.output(image,response.getOutputStream());
    }

    /**
     * 忘记密码重新修改密码
     * @return
     */
    @RequestMapping(value = "/forgetPassword")
    public String forgetPassword(){
        logger.info("填写信息申请新密码");
        return "/forgetPassword";
    }

    /**
     * 通过用户名和邮箱验证用户是否存在，并将随机生成的密码通过邮件发送
     * @param username
     * @param email
     * @return
     */
    @RequestMapping(value = "/randomGeneratorPassword")
    public String generatorPassword(Model model,
                                    @RequestParam(value = "username") String username,
                                    @RequestParam(value = "email") String email) throws Exception{
        logger.info("获取要修改的账号");
        String result = null;
        String subject = "Blog 密码找回！";
        StringBuffer text = new StringBuffer("尊敬的 "+username +"你生成的新密码是：");
        User user = userService.findUserByUsernameAndEmail(username,email);
        if(user != null){
            logger.info("随机生成新密码");
            String newPassword = StringUtils.generatorChar(8);
            logger.info("发送随机密码到绑定邮箱");
            text.append(newPassword+" 请尽快登录进行修改");
            MailUtil.SendEmail(subject,text.toString(),user.getEmail());
            logger.info("新密码发送成功");
            String BCrNewPassword = Utils.BCryptUtil(newPassword);
            userService.updatePasswordById(BCrNewPassword,user.getId());
            result = "/login";
        }else {
            logger.info("该用户不存在");
            model.addAttribute("errorMsg","用户不存在");
            result = "/forgetPassword";
        }
        return result;
    }
}
