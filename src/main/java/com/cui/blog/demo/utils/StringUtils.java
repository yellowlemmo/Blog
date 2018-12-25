package com.cui.blog.demo.utils;

public class StringUtils {

    public static boolean isNotEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return false;
        }
        return true;
    }
}
