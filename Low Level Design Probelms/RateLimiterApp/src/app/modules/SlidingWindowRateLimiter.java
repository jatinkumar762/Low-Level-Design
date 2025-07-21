package app.modules;

import app.interfaces.RateLimiter;
import java.util.LinkedList;
import java.util.Queue;
import javax.swing.Spring;

public class SlidingWindowRateLimiter implements RateLimiter {

    private int maxRequests;
    private long timeWindowMiliSeconds;
    private Queue<Long> lastRequestTimeStamp;

    public SlidingWindowRateLimiter(int maxRequests, long timeWindowMiliSeconds) {
        this.maxRequests = maxRequests;
        this.timeWindowMiliSeconds = timeWindowMiliSeconds;
        this.lastRequestTimeStamp = new LinkedList<>();
    }

    @Override
    public synchronized boolean allowRequest(String clientId) {

        long now = System.currentTimeMillis();

        if(!lastRequestTimeStamp.isEmpty() && (now - lastRequestTimeStamp.peek()) > timeWindowMiliSeconds){
            lastRequestTimeStamp.poll();
        }

        if(lastRequestTimeStamp.size() < maxRequests){
            lastRequestTimeStamp.add(now);
            return true;
        }

        return false;
    }
}
