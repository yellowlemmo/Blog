package com.cui.blog.demo.utils;

import com.cui.blog.demo.Service.PermissionService;
import com.cui.blog.demo.pojo.Permission;
import com.cui.blog.demo.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;
import org.springframework.util.ResourceUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

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

    /**
     * 根据角色获取链接
     * @param roles
     * @return
     */
    public  List<Permission> findPermissionByRole(List<Role> roles){
        return permissionService.findPermission(roles);
    }


    public static String getProperties(String path,String key){
        Properties properties = new Properties();
        String value = null;
        try {
            //通过输入流读取配置文件到内存
            InputStream inputStream = new BufferedInputStream(new FileInputStream(ResourceUtils.getFile(path)));
            //properties加载配置文件
            properties.load(inputStream);
            //通过关键词得到值
            value = properties.getProperty(key);

        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }

//    public static void main(String[] arg) throws Exception{
//        System.out.println(getProperties("classpath:mail.properties","add"));
//    }
}
