package app.interfaces;

public interface RateLimiter {
    boolean allowRequest(String clientId);
}
