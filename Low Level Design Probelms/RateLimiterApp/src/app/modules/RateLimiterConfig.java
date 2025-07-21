package app.modules;

import app.enums.RateLimiterAlgo;

public class RateLimiterConfig {

    private RateLimiterAlgo rateLimiterAlgo;
    private int capacity;
    private int refillRate;
    private long refillIntervalMillis;
    private int maxRequests;
    private long timeWindowMiliSeconds;

    public RateLimiterConfig(RateLimiterAlgo rateLimiterAlgo, int capacity, int refillRate, long refillIntervalMillis) {
        this.rateLimiterAlgo = rateLimiterAlgo;
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.refillIntervalMillis = refillIntervalMillis;
    }

    public RateLimiterConfig(RateLimiterAlgo rateLimiterAlgo, int maxRequests, long timeWindowMiliSeconds) {
        this.rateLimiterAlgo = rateLimiterAlgo;
        this.maxRequests = maxRequests;
        this.timeWindowMiliSeconds = timeWindowMiliSeconds;
    }

    public RateLimiterAlgo getRateLimiterAlgo() {
        return rateLimiterAlgo;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getRefillRate() {
        return refillRate;
    }

    public long getRefillIntervalMillis() {
        return refillIntervalMillis;
    }

    public int getMaxRequests() {
        return maxRequests;
    }

    public long getTimeWindowMiliSeconds() {
        return timeWindowMiliSeconds;
    }
}
