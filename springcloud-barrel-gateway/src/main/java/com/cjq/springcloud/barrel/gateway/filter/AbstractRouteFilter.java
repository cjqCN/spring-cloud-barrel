package com.cjq.springcloud.barrel.gateway.filter;

import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * @author chenjinquan
 */
public abstract class AbstractRouteFilter extends GenericFilterBean implements RouteFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        if (!shouldFilter(request)) {
            chain.doFilter(request, response);
            return;
        }
        doFilter1(request, response, chain);
    }

    abstract void doFilter1(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;


}
