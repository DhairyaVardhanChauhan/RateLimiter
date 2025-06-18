package com.dhairya.ratelimiter.service.impl;

import com.dhairya.ratelimiter.service.RateLimiter;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class TokenBucketService implements RateLimiter {

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public Long getTokenRefillDuration() {
        return tokenRefillDuration;
    }

    public AtomicLong getTokensAvailable() {
        return tokensAvailable;
    }

    public long getTokenRefillTimeStamp() {
        return tokenRefillTimeStamp;
    }

    public void setTokenRefillTimeStamp(long tokenRefillTimeStamp) {
        this.tokenRefillTimeStamp = tokenRefillTimeStamp;
    }

    private Long threshold = 5L;
    private final Long  tokenRefillDuration = 3000L;
    private final AtomicLong tokensAvailable = new AtomicLong(0);
    private volatile long tokenRefillTimeStamp = System.currentTimeMillis();

    public TokenBucketService() {
        this.threshold = threshold;
        this.tokensAvailable.set(threshold);
    }


    @Override
    synchronized public boolean tryAcquire() {
        // step1 add tokens to the bucket.
        long tokensToAdd = ((System.currentTimeMillis() - tokenRefillTimeStamp)/tokenRefillDuration)*threshold;
        System.out.println(tokensToAdd);
        if (tokensToAdd > 0 ) {
            tokensAvailable.set(Math.min(tokensAvailable.addAndGet(tokensToAdd) , threshold));
            tokenRefillTimeStamp = System.currentTimeMillis();
        }
        if(tokensAvailable.get() > 0){
            tokensAvailable.decrementAndGet();
            return true;
        }
        return false;
    }
}
