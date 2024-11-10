package com.secured.banking.security.handler;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.secured.banking.utility.constant.ApplicationVariable;
import jakarta.annotation.Nonnull;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

@Component
public class LoginAttemptHandler {

    private final LoadingCache<String, Integer> attemptsCache;

    public LoginAttemptHandler() {
        Integer userIpLockoutDuration = ApplicationVariable.USER_IP_LOCKOUT_DURATION;
        attemptsCache = CacheBuilder.newBuilder().expireAfterWrite(userIpLockoutDuration, TimeUnit.MINUTES).build(new CacheLoader<>() {
            @Nonnull
            @Override
            public Integer load(@Nonnull String key) {
                return 0;
            }
        });
    }

    public void loginSucceeded(HttpServletRequest servletRequest) {
        String key = getClientIP(servletRequest);
        attemptsCache.invalidate(key);
    }

    public void loginFailed(HttpServletRequest servletRequest) {
        String key = getClientIP(servletRequest);
        int attempts;
        try {
            attempts = attemptsCache.get(key);
        } catch (final ExecutionException exception) {
            attempts = 0;
        }
        attempts++;
        attemptsCache.put(key, attempts);
    }

    public boolean isBlocked(HttpServletRequest servletRequest) {
        String key = getClientIP(servletRequest);
        Integer maxAttempt = ApplicationVariable.USER_WRONG_PASSWORD_MAX_ATTEMPT;
        try {
            return attemptsCache.get(key) >= maxAttempt;
        } catch (final ExecutionException exception) {
            return false;
        }
    }

    public String getClientIP(HttpServletRequest servletRequest) {
        final String xfHeader = servletRequest.getHeader("X-Forwarded-For");
        if (xfHeader != null) {
            return xfHeader.split(",")[0];
        }
        return servletRequest.getRemoteAddr();
    }

}
