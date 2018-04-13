package springcloud.barrel.common.util;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;

/**
 * 获取用户信息
 *
 * @author chenjinquan
 */
@Data
@Slf4j
public class UserHolder implements Filter {

    private static String DEFAULT_USER_NAME = "DEFAULT_USER_NAME";
    private static Long DEFAULT_USER_ID = 123456789L;

    private static ThreadLocal<JwtPayLoad> jwtPayLoadThreadLocal;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        jwtPayLoadThreadLocal = new ThreadLocal<>();
        log.debug("UserHolder init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        JwtPayLoad jwtPayLoad = JwtUtil.parseTokenWithNotVerify(httpServletRequest);
        //设置用户信息
        setUp(jwtPayLoad);
        chain.doFilter(request, response);
        //清除用户信息
        clear();
    }

    @Override
    public void destroy() {
        log.debug("UserHolder destroy");
    }

    private void setUp(JwtPayLoad jwtPayLoad) {
        jwtPayLoadThreadLocal.set(jwtPayLoad);
    }


    private void clear() {
        jwtPayLoadThreadLocal.remove();
    }


    public static String getCurrentUserName() {
        return Optional.ofNullable(jwtPayLoadThreadLocal).map(x -> x.get()).map(JwtPayLoad::userName).orElse(DEFAULT_USER_NAME);
    }

    public static Long getCurrentUserId() {
        return Optional.ofNullable(jwtPayLoadThreadLocal).map(x -> x.get()).map(JwtPayLoad::userId).orElse(DEFAULT_USER_ID);
    }


}
