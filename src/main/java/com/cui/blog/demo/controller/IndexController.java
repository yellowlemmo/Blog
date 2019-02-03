package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.ArticleService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.Article;
import com.cui.blog.demo.pojo.Role;
import com.cui.blog.demo.pojo.User;
import com.cui.blog.demo.utils.MailUtil;
import com.cui.blog.demo.utils.SpringSecurityUtil;
import com.cui.blog.demo.utils.StringUtils;
import com.cui.blog.demo.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
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
                        @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                        @RequestParam(value = "pageSize",defaultValue = "10") int pageSize){
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        Page<Article> page = articleService.findAllBlog(pageableFactory.getPageable());
        model.addAttribute("page",page);
        return "index";
    }

    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String loginPage(Model model, String error, String logout){
        if (error != null) {
            model.addAttribute("error", "Your username and password is invalid.");
        }
        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

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
    public String loginError(Model model){
        logger.error("登录失败");
        model.addAttribute("errorMessage","用户名密码不正确！");
        return "login";
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
