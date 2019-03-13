package com.cui.blog.demo.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public abstract class GlobalParamter {

    //elasticsearch地址
    public static String ELASTICSEARCH_URL = "http://127.0.0.1:9200/blog/_search";

    /**
     * 通用数字
     */
    public static Integer ZERO = 0;

    /**
     * 请求响应代码
     */
    //成功
    public static Integer SUCCESS = 200;

    //地址不存在或者包含不支持的参数
    public static Integer BAD_REQUEST = 400;

    //未授权、认证失败
    public static Integer UNAUTHORIZED = 401;

    //资源不存在
    public static Integer NOT_FOUND = 404;

    //服务器内部错误
    public static Integer SERVER_ERROR = 500;

    //服务不可用，一般是系统超载
    public static Integer SERVICE_UNAVAILABLE = 503;

    //redis超时时间
    public static long REDIS_TIMROUT = 24;

    //超时时间类型(时分秒)
    public static TimeUnit REDIS_TIMETYPE = TimeUnit.HOURS;
}
