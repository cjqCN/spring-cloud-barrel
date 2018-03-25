package springcloud.barrel.common.pojo.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserDTO {

    private Long userId;

    private String userName;

    private String passwd;

    private String salt;

    private boolean status;

    private List<String> roles;

}
