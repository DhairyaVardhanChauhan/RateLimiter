package com.dhairya.ratelimiter.dto;


import java.util.concurrent.atomic.AtomicLong;

public class TokenBucket {
    private Long threshold;
    private Long refillDuration;
    private AtomicLong tokensAvailable;
    private volatile long lastRefillTime;
    public TokenBucket() {}
    public TokenBucket(Long threshold, Long refillDuration) {
        this.threshold = threshold;
        this.refillDuration = refillDuration;
        this.tokensAvailable = new AtomicLong(threshold);
        this.lastRefillTime = System.currentTimeMillis();
    }

    public Long getThreshold() {
        return threshold;
    }

    public void setThreshold(Long threshold) {
        this.threshold = threshold;
    }

    public Long getRefillDuration() {
        return refillDuration;
    }

    public void setRefillDuration(Long refillDuration) {
        this.refillDuration = refillDuration;
    }

    public AtomicLong getTokensAvailable() {
        return tokensAvailable;
    }

    public void setTokensAvailable(AtomicLong tokensAvailable) {
        this.tokensAvailable = tokensAvailable;
    }

    public long getLastRefillTime() {
        return lastRefillTime;
    }

    public void setLastRefillTime(long lastRefillTime) {
        this.lastRefillTime = lastRefillTime;
    }

    @Override
    public String toString() {
        return "TokenBucket{" +
                "threshold=" + threshold +
                ", refillDuration=" + refillDuration +
                ", tokensAvailable=" + tokensAvailable +
                ", lastRefillTime=" + lastRefillTime +
                '}';
    }
}
