package app.modules;

import app.interfaces.RateLimiter;

public class TokenBucketRateLimiter implements RateLimiter {

    private int capacity;
    private int refillRate;
    private long refillIntervalMillis;

    private double availableTokens;
    private long lastRefillTimeStamp;

    private final Object lock = new Object();

    public TokenBucketRateLimiter(int capacity, int refillRate, long refillIntervalMillis) {
        this.capacity = capacity;
        this.refillRate = refillRate;
        this.refillIntervalMillis = refillIntervalMillis;

        this.availableTokens = capacity;
        this.lastRefillTimeStamp = System.currentTimeMillis();
    }


    @Override
    public boolean allowRequest(String clientId) {

        synchronized (lock){

            refillTokens();

            if(availableTokens>=1){
                availableTokens--;
                return  true;
            } else {
                return false;
            }
        }
    }

    private void refillTokens(){

        long now = System.currentTimeMillis();
        long elapsed = now - lastRefillTimeStamp;

        if(elapsed >= refillIntervalMillis){
            //availableTokens = capacity;
            double tokensToAdd = (elapsed / (double) refillIntervalMillis) * refillRate;
            availableTokens = Math.min(capacity, availableTokens + tokensToAdd);
            lastRefillTimeStamp = now;
        }
    }
}
