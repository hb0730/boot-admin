package com.hb0730.boot.admin.security.model;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysUser;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * user_info
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
@Data
@EqualsAndHashCode
@ToString
public class UserInfo implements UserDetails {
    /**
     * username
     */
    private String username;
    /**
     * nickname
     */
    private String nickname;
    /**
     * password
     */
    private String password;
    /**
     * enabled
     */
    private Integer isEnabled;

    /**
     * 权限
     */
    private List<String> permissions;
    /**
     * 角色
     */
    private List<String> roles;

    @Override
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

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled == 1;
    }

    public static UserInfo convert(SysUser user, List<String> roleCodes,
                                   List<String> permissionCodes) {
        UserInfo userInfo = new UserInfo();
        // 用户名
        userInfo.setUsername(user.getUsername());
        // nickname
        userInfo.setNickname(user.getNickname());
        // 密码
        userInfo.setPassword(user.getPassword());
        // 角色
        userInfo.setRoles(roleCodes);
        // 权限
        userInfo.setPermissions(permissionCodes);
        // 是否启用
        userInfo.setIsEnabled(user.getEnabled());

        return userInfo;
    }
}
