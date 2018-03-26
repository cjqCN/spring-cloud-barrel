package com.cjq.springcloud.barrel.gateway.filter;

import com.cjq.springcloud.barrel.gateway.util.RouteUtil;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

/**
 * @author chenjinquan
 */
public abstract class TokenFilter extends AbstractRouteFilter {

    @Override
    public boolean shouldFilter(ServletRequest servletRequest) {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        return !RouteUtil.routeToOauthService(request);
    }
}
