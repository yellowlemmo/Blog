package com.cui.blog.demo.utils;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
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
    public static <T> String listToJson(List<T> list){
        Gson gson  = new Gson();
        String jsonArray = gson.toJson(list);
        return jsonArray;
    }

    /**
     * jsonArray转换为list
     * @param jsonArray
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonArray,Class clazz){
        Type type = new ParameterizedTypeImpl(clazz);
        List<T> list =  new Gson().fromJson(jsonArray, type);
        return list;
    }

    private static class ParameterizedTypeImpl implements ParameterizedType{
        Class clazz;

        public ParameterizedTypeImpl(Class clz){
            clazz = clz;
        }

        @Override
        public Type[] getActualTypeArguments() {
            return new Type[]{clazz};
        }

        @Override
        public Type getRawType() {
            return List.class;
        }

        @Override
        public Type getOwnerType() {
            return null;
        }
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
