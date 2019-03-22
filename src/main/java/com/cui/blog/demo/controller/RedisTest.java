package com.cui.blog.demo.controller;

import com.cui.blog.demo.base.BaseController;
import com.cui.blog.demo.utils.VerifyCodeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.nio.Buffer;

@Controller
public class RedisTest extends BaseController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping(value = "/redis")
    @ResponseBody
    public String redisTest(){
        stringRedisTemplate.opsForValue().set("czc","redis1 Test");
        return stringRedisTemplate.opsForValue().get("czc");
    }

}
