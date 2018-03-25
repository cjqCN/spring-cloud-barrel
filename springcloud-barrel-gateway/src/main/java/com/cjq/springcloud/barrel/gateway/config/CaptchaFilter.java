//package com.cjq.springcloud.barrel.gateway.config;
//
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.filter.GenericFilterBean;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@Slf4j
//public class CaptchaFilter extends GenericFilterBean {
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        String token = request.getHeader("Authorization");
//        if (token == null) {
//            response.setStatus(401);
//            return;
//        }
//        logger.info(token);
//        filterChain.doFilter(servletRequest, servletResponse);
//    }
//}
