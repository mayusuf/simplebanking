package com.secured.banking.utility.constant;

import java.util.concurrent.TimeUnit;

public class JwtVariable {
    public static final String CLAIM_USER = "usr";
    public static final String TOKEN_PREFIX = "Bearer";
    public static final Integer TOKEN_SUBSTRING_INDEX = 7;
    public static final String TOKEN_SECRET_KEY = "miu.2024_waa";
    public static final String SERVLET_REQUEST_HEADER = "Authorization";
    public static final Long EXPIRATION_TIME_TOKEN = TimeUnit.HOURS.toMillis(11L);
    public static final Long EXPIRATION_TIME_REFRESH_TOKEN = TimeUnit.HOURS.toMillis(11L);
}
