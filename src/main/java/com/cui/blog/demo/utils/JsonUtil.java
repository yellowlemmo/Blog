package com.cui.blog.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json工具类
 */
public class JsonUtil {

    /**
     * 利用Gson将map转为json字符串
     * @param map
     * @return
     */
    public static String mapTOJson(Map<String,Object> map){
        Gson gson = new Gson();
        String jsonObject = gson.toJson(map);
        return jsonObject;
    }

    /**
     * 利用Gson将list转为json
     * @param list
     * @return
     */
    public static String listToJson(List<Object> list){
        Gson gson  = new Gson();
        String jsonArray = gson.toJson(list);
        return jsonArray;
    }

    /**
     * elasticsearch搜素post的json请求体
     * @param queryContext
     * @return
     */
    public static String elasticsearchPostBody(String queryContext){
        Map<String,Object> map = new HashMap<>();
        map.put("title",queryContext);
        Map<String,Object> map1 = new HashMap<>();
        map1.put("context",queryContext);
        Map<String,Object> map2 = new HashMap<>();
        map2.put("match",map);
        Map<String,Object> map3 = new HashMap<>();
        map3.put("match",map1);

        List<Object> list = new ArrayList<>();
        list.add(map2);
        list.add(map3);
        Map<String,Object> should = new HashMap<>();
        should.put("should",list);
        Map<String,Object> bool = new HashMap<>();
        bool.put("bool",should);
        Map<String,Object> query = new HashMap<>();
        query.put("query",bool);
        String jsonContext = JsonUtil.mapTOJson(query);
        return jsonContext;
    }
}
