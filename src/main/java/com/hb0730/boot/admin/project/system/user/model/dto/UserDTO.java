package com.hb0730.boot.admin.project.system.user.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb0730.commons.lang.collection.CollectionUtils;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;

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
    /**
     * 用户账号
     */
    private String username;
    /**
     * 用户密码
     */
    @JsonIgnore
    private String password;

    /**
     * 用户角色(标识符)
     */
    private Collection<String> role;

    public void setRole(Collection<String> role) {
        if (CollectionUtils.isEmpty(role)) {
            return;
        }
        this.role = new ArrayList<>(role);
    }

    /**
     * 用户权限(标识符)
     */
    private Collection<String> permission;

    public void setPermission(Collection<String> permission) {
        if (CollectionUtils.isEmpty(permission)) {
            return;
        }
        this.permission = new ArrayList<>(permission);
    }
}
