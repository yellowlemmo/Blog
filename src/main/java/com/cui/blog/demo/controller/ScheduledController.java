package com.cui.blog.demo.controller;

import com.cui.blog.demo.Service.RedisTemplateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledController {

    private static final Logger logger = LoggerFactory.getLogger(ScheduledController.class);

    @Autowired
    private RedisTemplateService redisTemplateService;

    /**
     * 定时清理redis的热门和最近文章缓存（每5分钟执行一次）
     */
    @Scheduled(cron = "0 */5 * * * ?")
    public void clearRecentCache(){
        logger.info("开始清理recent文章缓存列表");
        //清理最新文章缓存
        redisTemplateService.delete("resentBlog::1");
        logger.info("recent列表缓存清理完成");
    }

    @Scheduled(cron = "0 */5 * * * ?")
    public void clearHostCache(){
        logger.info("开始清理host文章缓存列表");
        //清理热门文章缓存
        redisTemplateService.delete("hostBlog::1");
        logger.info("host列表缓存清理完成");
    }

}
