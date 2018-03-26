package com.cjq.springcloud.barrel.gateway.util;

import javax.servlet.http.HttpServletRequest;

public class RouteUtil {

    private final static String OAUTH_SERVICE_URI_PREFIX = "/cehn-oauth";
    private final static String OAUTH_SERVICE_URI_WORkPATH = OAUTH_SERVICE_URI_PREFIX + "/";

    public static boolean routeToOauthService(HttpServletRequest servletRequest) {
        String uri = servletRequest.getRequestURI().toString();
        boolean res = uri.startsWith(OAUTH_SERVICE_URI_WORkPATH) || uri.endsWith(OAUTH_SERVICE_URI_PREFIX);
        return res;
    }
}
