package com.cjq.springcloud.barrel.oauth.converter;

import com.cjq.springcloud.barrel.oauth.bean.User;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UserDetails;
import springcloud.barrel.common.pojo.dto.UserDTO;

/**
 * @author chenjinquan
 */
public class UserConverter implements UserConvertable<UserDTO> {

    @Override
    public UserDetails tranferUser(UserDTO userDTO) {
        if (userDTO == null) {
            return null;
        }
        User user = new User();
        user.setUserId(userDTO.getUserId());
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}
