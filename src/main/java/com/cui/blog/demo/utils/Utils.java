package com.cui.blog.demo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

public class Utils {
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
}
