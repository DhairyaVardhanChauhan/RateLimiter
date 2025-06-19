package com.dhairya.ratelimiter.dto;

import java.util.concurrent.atomic.AtomicLong;

public class RedisData {
    private AtomicLong tokensAvailable;
    private Long tokenRefillTimeStamp;
    private RedisData() {}
    public RedisData(long tokensAvailable, Long tokenRefillTimeStamp) {
        this.tokensAvailable = new AtomicLong(tokensAvailable);
        this.tokenRefillTimeStamp = tokenRefillTimeStamp;
    }

    public RedisData(AtomicLong tokensAvailable, Long tokenRefillTimeStamp) {
        this.tokensAvailable = tokensAvailable;
        this.tokenRefillTimeStamp = tokenRefillTimeStamp;
    }

    public AtomicLong getTokensAvailable() {
        return tokensAvailable;
    }

    public void setTokensAvailable(AtomicLong tokensAvailable) {
        this.tokensAvailable = tokensAvailable;
    }

    public Long getTokenRefillTimeStamp() {
        return tokenRefillTimeStamp;
    }

    public void setTokenRefillTimeStamp(Long tokenRefillTimeStamp) {
        this.tokenRefillTimeStamp = tokenRefillTimeStamp;
    }
}
