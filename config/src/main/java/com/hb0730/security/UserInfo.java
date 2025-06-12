package com.hb0730.security;

import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.spring.security.core.domain.IUserInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/6/12
 */
@Data
@EqualsAndHashCode
@ToString
public class UserInfo implements IUserInfo {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 昵称
     */
    private String nickname;
    /**
     * 头像
     */
    private String avatar;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 用户角色
     */
    private List<String> roles;
    /**
     * 用户权限
     */
    private List<String> permissions;
    /**
     * 是否启用
     */
    private Boolean status;

    public Collection<Authority> getAuthorities() {
        List<Authority> authorities = new ArrayList<>();
        //角色+权限
        if (CollectionUtil.isNotEmpty(this.permissions)) {
            authorities.addAll(this.permissions.stream().map(Authority::new).toList());
        }
        if (CollectionUtil.isNotEmpty(this.roles)) {
            authorities.addAll(
                    this.roles.stream().map(Authority::new).toList()
            );
        }
        return authorities;
    }
}
