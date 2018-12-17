package com.cui.blog.demo.utils;

public class StringUtils {

    public boolean isNotEmpty(String str){
        if(str.equals("") || str == null){
            return false;
        }
        return true;
    }
}
