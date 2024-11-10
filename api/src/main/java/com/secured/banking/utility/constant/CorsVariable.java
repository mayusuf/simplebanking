package com.secured.banking.utility.constant;

public class CorsVariable {
    public static final Boolean ALLOW_CREDENTIAL = true;
    public static final String ALLOWED_ORIGIN_PATTERN = "*";
    public static final String EXPOSED_HEADER = JwtVariable.SERVLET_REQUEST_HEADER;
    public static final String[] ALLOWED_HEADERS = {"Authorization", "Cache-Control", "Content-Type"};
    public static final String[] ALLOWED_METHODS = {"GET", "POST", "PUT", "DELETE", "PUT", "OPTIONS", "PATCH", "DELETE"};
}
