package com.cjq.springcloud.barrel.gateway.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;

/**
 * 权限校验过滤器，验证用户是否有权限
 *
 * @author chenjinquan
 */
@Slf4j
public class PermissionCheckFilter extends TokenFilter {
    @Override
    void doFilter1(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(request, response);
    }
}
