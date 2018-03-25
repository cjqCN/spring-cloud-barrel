package springcloud.barrel.common.pojo.dto;

import lombok.Data;

@Data
public class PermissionDTO {

    private Long permissionId;

    private String permissionCode;

    private String permissionName;

    private String uri;
}
