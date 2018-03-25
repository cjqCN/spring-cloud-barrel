package com.cjq.springcloud.barrel.upms.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import springcloud.barrel.common.pojo.dto.PermissionDTO;

import java.util.Arrays;
import java.util.List;

/**
 * @author jqChan
 * @date 2018/3/25
 */
@RestController
public class PermissionController {

    @GetMapping("/listPermissionByRole")
    public List<PermissionDTO> listPermissionByRole(@RequestParam("roleName") String roleName) {
        if ("root".equals(roleName)) {
            PermissionDTO permissionDTO = new PermissionDTO();
            permissionDTO.setPermissionId(123456789L);
            permissionDTO.setPermissionCode("resource::index");
            permissionDTO.setPermissionName("resource-index");
            permissionDTO.setUri("/resource/index");
            return Arrays.asList(permissionDTO);
        }
        return null;
    }
}
