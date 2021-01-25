package com.hb0730.boot.admin.security.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hb0730.boot.admin.project.system.user.model.dto.UserDTO;
import com.hb0730.commons.lang.collection.CollectionUtils;
import lombok.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;

/**
 * 登录用户信息
 *
 * @author bing_huang
 * @since 3.0.0
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(callSuper = true)
public class User extends UserDTO implements UserDetails {
    private static final String ROLE = "ROLE_";

    /**
     * 用户 token
     */
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
    @Getter
    @Setter
    private String ipaddr;
    /**
     * 浏览器类型
     */
    @Getter
    @Setter
    private String browser;

    /**
     * 操作系统
     */
    @Getter
    @Setter
    private String os;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    @JsonIgnore
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String permission = "";
        if (isAdmin()) {
            permission = "ROLE_ADMINISTRATOR";
            return AuthorityUtils.commaSeparatedStringToAuthorityList(permission);
        }
        Collection<String> permissionList = super.getPermission();
        if (!CollectionUtils.isEmpty(permissionList)) {
            permission = StringUtils.join(permissionList, ",");
        }
        Collection<String> roleList = super.getRole();
        if (!CollectionUtils.isEmpty(roleList)) {
            permission += "," + StringUtils.join(roleList, ",");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(permission);
    }

    public boolean isAdmin() {
        return getIsAdmin() == 1;
    }

    @Override
    @JsonIgnore
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
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return true 可以解锁
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     *
     * @return 提示已过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     *
     * @return true:可用
     */
    @Override
    public boolean isEnabled() {
        return getIsEnabled() == 1;
    }
}
