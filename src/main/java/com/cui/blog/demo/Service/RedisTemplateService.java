package com.cui.blog.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * redisTemplate接口
 */
@Service
public class RedisTemplateService {

    /**
     * 注入redis模版配置类
     */
    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 保存redis缓存数据key-value
     * @param key
     * @param object
     */
    public void saveRedisCache(String key,Object object,long timeout,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,object,timeout,timeUnit);
    }

    /**
     * 保存redis缓存数据key-map
     * @param key
     * @param hashMap
     */
    public void saveRedisMapCache(String key, Map<Object,Object> hashMap){
        redisTemplate.opsForHash().putAll(key,hashMap);
    }

    /**
     * 保存redis缓存数据key-set
     * @param key
     * @param set
     */
    public void saveRedisSetCache(String key,Set<Object> set){
        redisTemplate.opsForSet().add(key,set);
    }

    /**
     * 保存redis缓存数据key-list
     * @param key
     * @param list
     */
    public void saveRedisListCache(String key,List<Object> list){
        redisTemplate.opsForList().leftPush(key,list);
    }

    /**
     * 通过key获取值
     * @param key
     */
    public Object getValue(String key){
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除指定key的缓存数据
     * @param key
     * @return
     */
    public boolean delete(String key){
        return redisTemplate.delete(key);
    }


    /**
     * 批量删除缓存数据
     * @param keys
     * @return
     */
    public long deleteKeys(Collection<String> keys){
        return redisTemplate.delete(keys);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }


    /**
     * 为缓存数据重命名
     * @param oldKey
     * @param newKey
     */
    public void renameForCache(String oldKey,String newKey){
        redisTemplate.rename(oldKey,newKey);
    }

    /**
     * 将给定key的数据移动到index数据库
     * @param key
     * @param index
     */
    public boolean moveKeyToIndex(String key,int index){
        return redisTemplate.move(key,index);
    }

    /**
     * 根据key模糊查询value(String)
     * @param key
     * @return
     */
    public Set<String> getKeys(String key){
        return redisTemplate.keys(key);
    }

    /**
     * 设置缓存数据到期时间
     * @param key
     * @param timeout
     * @param unit
     */
    public boolean setExpire(String key, long timeout, TimeUnit unit){
        return redisTemplate.expire(key,timeout,unit);
    }

    /**
     * 获取缓存数据到期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key);
    }

}
