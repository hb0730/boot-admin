package com.hb0730.security.utils;

import com.hb0730.security.model.dto.AuthorityDto;
import com.hb0730.security.model.dto.LoginInfoDto;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * 安全工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/22
 */
public class SecurityUtil {

    /**
     * 获取当前用户
     *
     * @return .
     */
    public static LoginInfoDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null) {
            return null;
        }
        if (authentication.getPrincipal() instanceof LoginInfoDto) {
            return (LoginInfoDto) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 获取当前用户id
     *
     * @return .
     */
    public static Integer getUserId() {
        LoginInfoDto currentUser = getCurrentUser();
        if (currentUser != null) {
            return currentUser.getUser().getId();
        }
        return null;
    }

    /**
     * 获取当前username
     *
     * @return .
     */
    public static String getUsername() {
        LoginInfoDto currentUser = getCurrentUser();
        if (null != currentUser) {
            return currentUser.getUser().getUsername();
        }
        return null;
    }

    /**
     * 获取当前用户权限
     *
     * @return .
     */
    @SuppressWarnings("unchecked")
    public static Collection<AuthorityDto> getAuthorities() {
        UserDetails currentUser = getCurrentUser();
        if (currentUser != null) {
            return (Collection<AuthorityDto>) currentUser.getAuthorities();
        }
        return null;
    }
}
