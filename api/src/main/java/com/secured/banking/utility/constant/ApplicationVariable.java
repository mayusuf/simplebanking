package com.secured.banking.utility.constant;

public class ApplicationVariable {
    public static final Integer USER_IP_LOCKOUT_DURATION = 5;
    public static final Integer USER_WRONG_PASSWORD_MAX_ATTEMPT = 4;
    public static final Integer PASSWORD_MINIMUM_LENGTH = 10;
    public static final Integer PASSWORD_VALIDITY = 60000;
    public static final Integer PASSWORD_MINIMUM_COMPLEXITY = 3;
    public static final Integer PASSWORD_ALERT_AFTER = 15;
    public static final String[] PASSWORD_RESTRICTED_WORDS = {"admin", "administrator"};
    public static final Integer PASSWORD_MINIMUM_RESTRICTED_HISTORY = 5;
}
