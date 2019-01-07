package com.cui.blog.demo.controller;


import com.cui.blog.demo.Service.ArticleClassifyService;
import com.cui.blog.demo.Service.PermissionService;
import com.cui.blog.demo.Service.RoleService;
import com.cui.blog.demo.Service.UserService;
import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.base.PageableFactory;
import com.cui.blog.demo.pojo.ArticleClassify;
import com.cui.blog.demo.pojo.Permission;
import com.cui.blog.demo.pojo.Role;
import com.cui.blog.demo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@PreAuthorize("hasAnyAuthority('Role_admin')")
@RequestMapping(value = "/admin")
public class AdminController extends BaseController {

    /**
     * 用户service
     */
    @Autowired
    private UserService userService;

    /**
     * 博客分类service
     */
    @Autowired
    private ArticleClassifyService articleClassifyService;

    /**
     * 管理员权限管理service
     */
    @Autowired
    private PermissionService permissionService;

    /**
     * 角色service
     */
    @Autowired
    private RoleService roleService;

    /**
     * 管理员主页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/index")
    public String adminIndex(Model model){
        List<Permission> permissions = permissionService.findAll();
        model.addAttribute("permissions",permissions);
        return "/admin/index";
    }

    /**
     * 用户列表
     * @param model
     * @param username
     * @param pageIndex
     * @param pageSize
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/users")
    public String userList(Model model,
                           String username,
                           @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                           @RequestParam(value = "pageSize",defaultValue = "10") int pageSize) throws Exception{
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        Page<User> page = userService.findUserByUsername(username,pageableFactory.getPageable());
        model.addAttribute("page",page);
        return "/admin/userlist";
    }

    /**
     * 博客分类初始化
     * @return
     */

    @RequestMapping(value = "/initArticleClassify")
    public String initArticleClassify() throws Exception{

        return "/admin/initArticleClassify";
    }

    /**
     * 新增博客分类
     * @param articleClassify
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/saveArticleClassify")
    public String saveArticleClassify(Model model,ArticleClassify articleClassify) throws Exception{
        System.out.println(articleClassify.getClassifyName());
        articleClassifyService.saveArticleClassify(articleClassify);
        return "redirect:/admin/articleClassifyList";
    }

    /**
     * 博客分类列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/articleClassifyList")
    public String articleClassifyList(Model model,String classifyName,
                                      @RequestParam(value = "pageIndex",defaultValue = "0") int pageIndex,
                                      @RequestParam(value = "pageSize",defaultValue = "5") int pageSize) throws Exception{
        PageableFactory pageableFactory = new PageableFactory(pageIndex,pageSize);
        Page<ArticleClassify> page = articleClassifyService.articleClassifyList(classifyName,pageableFactory.getPageable());
        model.addAttribute("page",page);
        return "/admin/articleClassifyList";
    }

    /**
     * 初始化管理员增加新的管理权限
     * @return
     */
    @RequestMapping(value = "/addPermission")
    public String addPermission(Model model){
        List<Role> roles = roleService.findAllRole();
        model.addAttribute("roles",roles);
        return "/admin/addPermission";
    }

    /**
     * 保存管理要管理的权限
     * @param permission
     * @return
     */
    @RequestMapping(value = "/newPermission")
    public String newPermission(Permission permission){
        permissionService.savePermission(permission);
        return "redirect:/admin/index";
    }

}
