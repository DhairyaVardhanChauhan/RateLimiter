package com.dhairya.ratelimiter.controller;

import com.dhairya.ratelimiter.service.RateLimiter;
import com.dhairya.ratelimiter.service.impl.TokenBucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Autowired
    TokenBucketService tokenBucketService;

    @GetMapping("/test")
    public void test(){
        if(tokenBucketService.tryAcquire()){
            System.out.println("Request hit successfully");
            System.out.println(tokenBucketService.getTokensAvailable());
        }
        else{
            System.out.println(tokenBucketService.getTokensAvailable());
            System.out.println("Request hit failed");
        }

    }

}
