package com.cjq.springcloud.barrel.gateway.filter;

import com.cjq.springcloud.barrel.gateway.util.UserInfoHolder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import springcloud.barrel.common.util.JwtPayLoad;
import springcloud.barrel.common.util.JwtUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.cjq.springcloud.barrel.gateway.util.Contants.UNAUTHORIZED_HTTP_STATUS;

/**
 * 验证过滤器，验证token是否有效
 *
 * @author chenjinquan
 */
@Slf4j
public class CaptchaFilter extends TokenFilter {

    private static final String TOKEN_KEY = "123";

    @Override
    public void doFilter1(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        try {
            JwtPayLoad jwtPayLoad = JwtUtil.parseTokenBySHA256(request, TOKEN_KEY);
            // UserInfoHolder 放置当前验证信息
            UserInfoHolder.recover(jwtPayLoad);
            if (StringUtils.isEmpty(jwtPayLoad.userName())) {
                response.setStatus(UNAUTHORIZED_HTTP_STATUS);
                return;
            }
        } catch (Exception ex) {
            response.setStatus(UNAUTHORIZED_HTTP_STATUS);
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
