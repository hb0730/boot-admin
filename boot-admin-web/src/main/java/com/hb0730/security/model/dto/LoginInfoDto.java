package com.hb0730.security.model.dto;

import cn.hutool.core.collection.CollectionUtil;
import com.hb0730.biz.dto.sys.system.UserLoginDto;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * 登录用户信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
@Setter
@Getter
public class LoginInfoDto implements UserDetails {
    private UserLoginDto user;
    private List<String> roles;
    private List<String> permissions;

    @Override
    public Collection<AuthorityDto> getAuthorities() {
        List<AuthorityDto> authorities = new ArrayList<>();
        //角色+权限
        if (CollectionUtil.isNotEmpty(this.permissions)) {
            authorities.addAll(this.permissions.stream().map(AuthorityDto::new).toList());
        }
        if (CollectionUtil.isNotEmpty(this.roles)) {
            authorities.addAll(
                    this.roles.stream().map(AuthorityDto::new).toList()
            );
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getUsername();
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
        return Boolean.TRUE.equals(this.user.getEnabled());
    }

    /**
     * 是否是管理员
     *
     * @return true 是管理员
     */
    public boolean isAdmin() {
        return Boolean.TRUE.equals(this.user.getAdmin());
    }
}
