package com.hb0730.boot.admin.security.model;

import com.google.common.collect.Sets;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.role.model.dto.SystemRoleDTO;
import com.hb0730.boot.admin.project.system.user.model.dto.LoginUserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * <p>
 * 登录用户身份权限
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class LoginUser extends LoginUserDTO implements UserDetails {
    private static final String ROLE = "ROLE_";
    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 登陆时间
     */
    private Long loginTime;

    /**
     * 过期时间
     */
    private Long expireTime;

    /**
     * 登录IP地址
     */
    private String ipaddr;

    /**
     * 登录地点
     */
    private String loginLocation;

    /**
     * 浏览器类型
     */
    private String browser;

    /**
     * 操作系统
     */
    private String os;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getLoginTime() {
        return loginTime;
    }

    public void setLoginTime(Long loginTime) {
        this.loginTime = loginTime;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public String getIpaddr() {
        return ipaddr;
    }

    public void setIpaddr(String ipaddr) {
        this.ipaddr = ipaddr;
    }

    public String getLoginLocation() {
        return loginLocation;
    }

    public void setLoginLocation(String loginLocation) {
        this.loginLocation = loginLocation;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String perms = "";
        List<SystemPermissionDTO> permissions = super.getPermissions();
        if (!CollectionUtils.isEmpty(permissions)) {
            perms = StringUtils.join(permissions.parallelStream().map(SystemPermissionDTO::getMark).toArray(), ",");
        }
        List<SystemRoleDTO> roles = super.getRoles();
        if (!CollectionUtils.isEmpty(roles)) {
            Set<String> s = Sets.newConcurrentHashSet();
            roles.parallelStream().forEach(role -> {
                s.add(ROLE + role.getEnname());
            });
            perms += "," + StringUtils.join(s.toArray(), ",");
        }
        return AuthorityUtils.commaSeparatedStringToAuthorityList(perms);
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
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 指示是否已过期的用户的凭据(密码),过期的凭据防止认证
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 是否可用 ,禁用的用户不能身份验证
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}
