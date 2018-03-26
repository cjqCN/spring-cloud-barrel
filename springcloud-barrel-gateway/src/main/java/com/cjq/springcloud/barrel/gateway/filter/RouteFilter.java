package com.cjq.springcloud.barrel.gateway.filter;


import javax.servlet.Filter;
import javax.servlet.ServletRequest;

/**
 * @author chenjinquan
 */
public interface RouteFilter extends Filter {

    /**
     * 是否进行过滤
     *
     * @param servletRequest
     * @return
     */
    boolean shouldFilter(ServletRequest servletRequest);
}
