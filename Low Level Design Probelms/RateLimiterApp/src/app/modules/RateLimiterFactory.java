package app.modules;

import app.interfaces.RateLimiter;

public class RateLimiterFactory {

    public static RateLimiter createRateLimiter(RateLimiterConfig config){

        switch (config.getRateLimiterAlgo()){
            case TOKEN_BUCKET -> {
                new TokenBucketRateLimiter(config.getCapacity(), config.getRefillRate(), config.getRefillIntervalMillis());
            }
            case SLIDING_WINDOW -> {
                 new SlidingWindowRateLimiter(config.getMaxRequests(), config.getTimeWindowMiliSeconds());
            }
            default -> throw new RuntimeException("Not Supported");
        }

        return null;
    }
}
