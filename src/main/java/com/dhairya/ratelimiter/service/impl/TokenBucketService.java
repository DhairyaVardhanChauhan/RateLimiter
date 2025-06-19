package com.dhairya.ratelimiter.service.impl;

import com.dhairya.ratelimiter.dto.RedisData;
import com.dhairya.ratelimiter.service.RateLimiter;
import com.dhairya.ratelimiter.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TokenBucketService implements RateLimiter {

    @Autowired
    private RedisService redisService;

    private Long threshold = 5L;
    private final Long  tokenRefillDuration = 20000L;

    @Override
    public boolean tryAcquire(Long userId) {
        String key = "RATE_LIMITER_"+userId;
        RedisData redisData = redisService.getKeyAs(key, RedisData.class);
        if(redisData == null) {
            redisData = new RedisData(5,System.currentTimeMillis());
        }
        Long tokenRefillTimeStamp = redisData.getTokenRefillTimeStamp();
        AtomicLong tokensAvailable = redisData.getTokensAvailable();
        // step1 add tokens to the bucket.
        long tokensToAdd = ((System.currentTimeMillis() - tokenRefillTimeStamp)/tokenRefillDuration)*threshold;
        System.out.println(tokensToAdd);
        if (tokensToAdd > 0 ) {
            tokensAvailable.set(Math.min(tokensAvailable.addAndGet(tokensToAdd) , threshold));
            tokenRefillTimeStamp = System.currentTimeMillis();
        }
        if(tokensAvailable.get() > 0){
            tokensAvailable.decrementAndGet();
            redisService.setKey(key,new RedisData(tokensAvailable,tokenRefillTimeStamp),Duration.ofSeconds(60*5));
            return true;
        }
        return false;
    }
}
