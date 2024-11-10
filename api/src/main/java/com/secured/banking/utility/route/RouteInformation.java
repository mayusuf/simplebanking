package com.secured.banking.utility.route;

public class RouteInformation {


    public static final String API_ROUTE_V1 = "/api/v1";
    public static final String REGISTRATION_ROUTE = "/registration";
    public static final String SIGN_IN_ROUTE = "/sign-in";

    public static final String USER_ROUTE = API_ROUTE_V1 + "/user";
    public static final String DASHBOARD_ROUTE = API_ROUTE_V1 + "/dashboard";

    public static final String ACCOUNTS_ROUTE = API_ROUTE_V1 + "/accounts";
    public static final String ADD_USER_ROUTE = USER_ROUTE + "/add";
    public static final String UPDATE_USER_ROUTE = USER_ROUTE + "/update";
    public static final String DEPOSIT_ROUTE = API_ROUTE_V1 + "/deposit";

    public static final String WITHDRAW_ROUTE = API_ROUTE_V1 + "/withdraw";

}
