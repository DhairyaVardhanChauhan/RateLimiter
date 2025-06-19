package com.dhairya.ratelimiter.service;

public interface RateLimiter {
    boolean tryAcquire(Long userId);
}
