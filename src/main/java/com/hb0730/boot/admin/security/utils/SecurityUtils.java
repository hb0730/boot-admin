package com.hb0730.boot.admin.security.utils;

import cn.hutool.extra.spring.SpringUtil;
import com.hb0730.boot.admin.security.configuration.WebSecurityConfiguration;
import com.hb0730.boot.admin.security.model.User;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 安全服务工具类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public class SecurityUtils {

    /**
     * 获取当前登录的用户
     *
     * @return {@link User}
     */
    @Nullable
    public static User getCurrentUser() {
        return getAuthentication() == null ? null : (User) getAuthentication().getPrincipal();
    }

    /**
     * 获取Authentication
     *
     * @return {@link Authentication}
     */
    @Nullable
    public static Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取密码加密bean
     *
     * @return {@link PasswordEncoder}
     * @see WebSecurityConfiguration#passwordEncoder()
     */
    @NonNull
    public static PasswordEncoder getPasswordEncoder() {
        return SpringUtil.getBean(PasswordEncoder.class);
    }
}
