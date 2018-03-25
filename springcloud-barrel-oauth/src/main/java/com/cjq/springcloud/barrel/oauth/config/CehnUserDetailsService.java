package com.cjq.springcloud.barrel.oauth.config;


import com.cjq.springcloud.barrel.oauth.converter.UserConvertable;
import com.cjq.springcloud.barrel.oauth.feign.FeignClientUpmsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import springcloud.barrel.common.pojo.dto.UserDTO;

/**
 * @author chenjinquan
 */
public class CehnUserDetailsService implements UserDetailsService {

    FeignClientUpmsService feignClientUpmsService;

    private final UserConvertable userConvertable;

    public CehnUserDetailsService(UserConvertable userConvertable,
                                  FeignClientUpmsService feignClientUpmsService) {
        this.userConvertable = userConvertable;
        this.feignClientUpmsService = feignClientUpmsService;
    }

    @Override
    public UserDetails loadUserByUsername(String acount) throws UsernameNotFoundException {
        UserDTO userDTO = feignClientUpmsService.viewUserByUserName(acount);
        if (userDTO == null) {
            throw new UsernameNotFoundException("The user does not exist.");
        }
        UserDetails userDetails = userConvertable.tranferUser(userDTO);
        return userDetails;
    }


}
