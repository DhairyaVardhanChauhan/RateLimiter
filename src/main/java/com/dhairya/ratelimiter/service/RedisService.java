package com.dhairya.ratelimiter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class RedisService {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    public void setKey(String key, Object value, Duration ttl) {
        redisTemplate.opsForValue().set(key,value,ttl);
    }
    public String getKey(String key){
        return (String) redisTemplate.opsForValue().get(key);
    }
    public <T> T getKeyAs(String key,Class<T> clazz){
        Object obj = redisTemplate.opsForValue().get(key);
        return clazz.cast(obj);
    }

}
