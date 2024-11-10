package com.secured.banking.utility.message;

public abstract class ErrorMessage {

    public static final String PASSWORD_EMPTY = "Password is empty";
    public static final String ENCODED_PASSWORD_EMPTY = "Encoded Password is empty";

    public static final String NO_ALGORITHM_INITIALIZED = "No EncodeAlgorithm Initialized";

    public static final String ACCESS_DENIED = "Access denied";
    public static final String TOKEN_NOT_FOUND = "Token not found";
    public static final String INTERNAL_FILTER_FAILED = "Internal Filter Failed :: ";
    public static final String USER_IP_BLOCK = "User ip is block";
    public static final String USERNAME_EXIST = "username not available!";

}
