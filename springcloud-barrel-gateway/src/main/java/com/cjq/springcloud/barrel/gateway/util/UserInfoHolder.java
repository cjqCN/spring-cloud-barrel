package com.cjq.springcloud.barrel.gateway.util;

import com.netflix.zuul.ZuulFilter;
import springcloud.barrel.common.pojo.dto.PermissionDTO;
import springcloud.barrel.common.util.JwtPayLoad;

import java.util.List;

/**
 * @author chenjinquan
 */
public class UserInfoHolder extends ZuulFilter {

    private static ThreadLocal<JwtPayLoad> userThreadLocal = new ThreadLocal<>();

    public static void clear() {
        userThreadLocal.remove();
    }

    public static void recover(JwtPayLoad jwtPayLoad) {
        userThreadLocal.set(jwtPayLoad);
    }

    public static String currentUserName() {
        return currentJwtPayLoad().userName();
    }

    public static List<String> currentUserRoles() {
        return currentJwtPayLoad().roles();
    }

    public static JwtPayLoad currentJwtPayLoad() {
        return userThreadLocal.get();
    }

    public List<PermissionDTO> currentUserPermission() {
        throw new RuntimeException("Not support now");
    }

    public void setUserInfo(JwtPayLoad jwtPayLoad) {
        userThreadLocal.set(jwtPayLoad);
    }

    @Override
    public String filterType() {
        return "post";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     * 在完成访问时，清除验证信息
     */
    @Override
    public Object run() {
        clear();
        return null;
    }


}




