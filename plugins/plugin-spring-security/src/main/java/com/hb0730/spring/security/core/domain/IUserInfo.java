package com.hb0730.spring.security.core.domain;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/6/11
 */
public interface IUserInfo extends com.hb0730.base.meta.IUserInfo {

    /**
     * 获取用户权限
     *
     * @return 用户权限集合
     */
    default Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }
}
