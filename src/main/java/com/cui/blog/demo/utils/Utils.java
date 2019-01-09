package com.cui.blog.demo.utils;

import com.cui.blog.demo.Service.PermissionService;
import com.cui.blog.demo.pojo.Permission;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

import java.util.List;

public class Utils {

    @Autowired
    private PermissionService permissionService;

    //md5加密
    public static String md5Util(String content){
        byte[] bytes = content.getBytes();
        String md5Content = DigestUtils.md5DigestAsHex(bytes);
        return md5Content;
    }

    //bCrypt加密
    public static String BCryptUtil(String content){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String bCryptContent = bCryptPasswordEncoder.encode(content);
        return bCryptContent;
    }

    public  List<Permission> findPermissionByRole(List<Role> roles){
        return permissionService.findPermission(roles);
    }
}
