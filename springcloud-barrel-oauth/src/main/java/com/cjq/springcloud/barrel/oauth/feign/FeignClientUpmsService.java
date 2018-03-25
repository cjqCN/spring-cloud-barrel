package com.cjq.springcloud.barrel.oauth.feign;


import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import springcloud.barrel.common.pojo.dto.UserDTO;

/**
 * @author chenjinquan
 */
@Component
@FeignClient(name = "cehn-upms", fallback = FeignClientUpmsService.FeignClientUpmsServiceFallbackImpl.class)
public interface FeignClientUpmsService {

    /**
     * 通过username获取用户信息
     *
     * @param userName
     * @return
     */
    @RequestMapping(value = "/viewUserByName", method = RequestMethod.GET)
    UserDTO viewUserByUserName(@RequestParam("userName") String userName);

    /**
     * 发生异常的时候
     */
    @Component
    class FeignClientUpmsServiceFallbackImpl implements FeignClientUpmsService {

        @Override
        public UserDTO viewUserByUserName(String userName) {
            return null;
        }
    }
}
