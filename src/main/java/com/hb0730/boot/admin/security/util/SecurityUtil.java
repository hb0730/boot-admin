package com.hb0730.boot.admin.security.util;

import com.hb0730.boot.admin.security.model.Authority;
import com.hb0730.boot.admin.security.model.UserInfo;
import lombok.experimental.UtilityClass;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@UtilityClass
public class SecurityUtil {
    /**
     * 获取当前用户
     *
     * @return .
     */
    public UserInfo getCurrentUser() {
        SecurityContext context = SecurityContextHolder.getContext();
        return (UserInfo) context.getAuthentication()
            .getCredentials();
    }

    /**
     * 获取当前用户名
     *
     * @return .
     */
    public String getCurrentUsername() {
        return (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

    /**
     * 获取当前权限, 权限是: 权限+角色
     *
     * @return .
     */
    public Collection<Authority> getCurrentPermission() {
        return getCurrentUser().getAuthorities();
    }
}
