package com.dhairya.ratelimiter.controller;

import com.dhairya.ratelimiter.dto.TokenBucket;
import com.dhairya.ratelimiter.service.RateLimiter;
import com.dhairya.ratelimiter.service.RedisService;
import com.dhairya.ratelimiter.service.impl.TokenBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TokenBucketService tokenBucketService;
    @Autowired
    RedisService redisService;

    @GetMapping("/test")
    public void test(@RequestParam Long userId){
        if(tokenBucketService.tryAcquire(userId)){
            System.out.println("Request hit successfully");
        }
        else{
            System.out.println("Request hit failed");
        }

    }

}
