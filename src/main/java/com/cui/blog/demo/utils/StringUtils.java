package com.cui.blog.demo.utils;

import java.util.Random;

public class StringUtils {

    /**
     * 字符串
     */
    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(String str){
        if(str == null || "".equals(str.trim())){
            return false;
        }
        return true;
    }

    /**
     * 返回一个定长的随机字符串(包含大小写字母、数字)
     * @param length
     * @return
     */
    public static String generatorChar(int length){
        StringBuffer stringBuffer = new StringBuffer();
        Random random = new Random();
        for (int i = 0 ; i<length; i++){
            stringBuffer.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        }
        return stringBuffer.toString();
    }
}
