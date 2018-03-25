package com.cjq.springcloud.barrel.upms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.barrel.common.pojo.dto.UserDTO;

import java.util.Arrays;

/**
 * @author jqChan
 * @date 2018/3/25
 */
@RestController
public class UserController {

    @GetMapping("/viewUserByName")
    public UserDTO viewUserByName(@RequestParam("userName") String userName) {
        if ("chenjinquan".equals(userName)) {
            UserDTO userDTO = new UserDTO();
            userDTO.setUserId(123456789L);
            userDTO.setUserName("chenjinquan");
            userDTO.setPasswd("123456");
            userDTO.setSalt("abc");
            userDTO.setStatus(true);
            userDTO.setRoles(Arrays.asList("root"));
            return userDTO;
        }
        return null;
    }

}
