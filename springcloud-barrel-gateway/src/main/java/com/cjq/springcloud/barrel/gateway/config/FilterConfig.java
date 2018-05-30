package com.cjq.springcloud.barrel.gateway.config;

import com.cjq.springcloud.barrel.gateway.filter.CaptchaFilter;
import com.cjq.springcloud.barrel.gateway.filter.PermissionCheckFilter;
import com.cjq.springcloud.barrel.gateway.util.UserInfoHolder;
import com.netflix.zuul.ZuulFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenjinquan
 */
//@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean captchaFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new CaptchaFilter());
        registration.addUrlPatterns("/*");
        registration.setName("CaptchaFilter");
        registration.setOrder(1);
        return registration;
    }

    @Bean
    public FilterRegistrationBean permissionCheckFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new PermissionCheckFilter());
        registration.addUrlPatterns("/*");
        registration.setName("PermissionCheckFilter");
        registration.setOrder(2);
        return registration;
    }

    @Bean
    public ZuulFilter zuulFilter() {
        return new UserInfoHolder();
    }

}
