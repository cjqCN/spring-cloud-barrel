package com.cjq.springcloud.barrel.oauth.converter;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @param <T>
 * @author chenjinquan
 */
public interface UserConvertable<T> {

    /**
     * t 转换成 UserDetails
     *
     * @param t
     * @return
     */
    UserDetails tranferUser(T t);
}
