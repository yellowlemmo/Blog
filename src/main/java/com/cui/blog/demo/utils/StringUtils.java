package com.cui.blog.demo.utils;

import org.elasticsearch.common.util.ObjectArray;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    /**
     * 返回当前时间
     * @return
     */
    public static String getCurrentTime(){
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyymmdd");
        String currentDateStr = simpleDateFormat.format(date);
        return currentDateStr;
    }

    /**
     * 序列化list为字节数组
     * @param list
     * @param <T>
     * @return
     */
    public static <T> byte[] serialize(List<T> list){
        if(list == null){
            throw new NullPointerException("list is null");
        }
        byte[] result = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream ops = null;
        try{
            bos = new ByteArrayOutputStream();
            ops = new ObjectOutputStream(bos);
            for (T obj:list) {
                ops.writeObject(obj);
            }
            ops.writeObject(null);
            ops.close();
            bos.close();
            result = bos.toByteArray();
        }catch (Exception e){
            e.printStackTrace();
        }
        return result;
    }
}
