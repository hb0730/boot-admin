package com.hb0730.boot.admin.project.system.user.info.model.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 用户信息(包含用户账号，用户详情以及用户权限组织信息)
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString
public class UserDTO extends UserInfoDTO {
    private String username;
    private String password;

}
