package app;

import app.interfaces.RateLimiter;
import app.modules.RateLimiterConfig;
import app.modules.RateLimiterFactory;
import java.util.HashMap;
import java.util.Map;

public class RateLimiterService {

    private static RateLimiterService instance = null;
    private Map<String, RateLimiterConfig> userConfigMap;
    private Map<String, RateLimiter> userRateLimiter;

    private RateLimiterService() {
        this.userConfigMap = new HashMap<>();
        this.userRateLimiter = new HashMap<>();
    }

    public static RateLimiterService getInstance(){

        synchronized (RateLimiterService.class){

            if(instance == null){
                instance = new RateLimiterService();
            }
        }

        return instance;
    }

    public boolean updateRateLimiterConfig(String userId, RateLimiterConfig config){
        userConfigMap.put(userId, config);

        updateRateLimiter(userId, config);

        return true;
    }


    private boolean updateRateLimiter(String userId, RateLimiterConfig config){

        RateLimiter rateLimiter = RateLimiterFactory.createRateLimiter(config);

        userRateLimiter.put(userId, rateLimiter);

        return true;
    }

    public boolean allowRequest(String userId){

        RateLimiter rateLimiter = userRateLimiter.get(userId);

        return rateLimiter.allowRequest(userId);
    }
}
