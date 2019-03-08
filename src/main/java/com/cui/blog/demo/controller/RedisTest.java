package com.cui.blog.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/redis")
    @ResponseBody
    public String redisTest(){
        stringRedisTemplate.opsForValue().set("czc","redis1 Test");
        return stringRedisTemplate.opsForValue().get("czc");
    }
}
