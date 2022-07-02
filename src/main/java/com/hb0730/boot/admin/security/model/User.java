package com.hb0730.boot.admin.security.model;

import cn.hutool.core.collection.CollUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 用户信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 3.0.0
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends UserDTO implements UserDetails {
    /**
     * 用户 token
     */
    @JsonIgnore
    private String token;

    /**
     * 用户登录时间
     */
    private Date loginTime;
    /**
     * 用户过期时间
     */
    private Date expireTime;
    /**
     * 登录IP地址
     */
    private String ipaddr;
    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String permission = "";
        if (isAdmin()) {
            permission = "ROLE_ADMINISTRATOR";
            return AuthorityUtils.commaSeparatedStringToAuthorityList(permission);
        }
        Collection<String> permissionList = super.getPermission();
        if (CollUtil.isNotEmpty(permissionList)) {
            permission = StringUtils.join(permissionList, ",");
        }
        Collection<String> roleList = super.getRole();
        if (CollUtil.isNotEmpty(roleList)) {
            permission += "," + StringUtils.join(roleList, ",");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(permission);
    }

    @JsonIgnore
    public boolean isAdmin() {
        return getIsAdmin() == 1;
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    /**
     * 账户是否未过期,过期无法验证
     *
     * @return true: 未过期
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return true 可以解锁
     */
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return 提示已过期
     */
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return true:可用
     */
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        return getIsEnabled() == 1;
    }

    public void setEnabled(Boolean enabled) {
        setIsEnabled(enabled ? 1 : 0);
    }
}
