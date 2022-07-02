package com.hb0730.boot.admin.security.handler;

import cn.hutool.core.util.StrUtil;
import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * 权限自定义处理
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Component("permissionHandler")
public class PermissionCustomHandler {
    private static final String DEFAULTROLEPREFIX = "ROLE_";

    /**
     * 验证用户是否具备某权限,(不推荐使用此方法,尽可能的使用security自带的)
     *
     * @param target     当前目标
     * @param permission 权限字符串
     * @return 用户是否具备某权限
     * @see org.springframework.security.access.expression.SecurityExpressionRoot#hasAnyAuthority(String...)
     */
    public boolean hasAnyAuthority(Object target, String... permission) {
        PreAuth annotation = target.getClass().getAnnotation(PreAuth.class);
        if (null == annotation) {
            return hasAnyAuthorityName(null, permission);
        }
        String value = annotation.value();
        if (StrUtil.isBlank(value)) {
            return hasAnyAuthorityName(null, permission);
        } else {
            return hasAnyAuthorityName(value, permission);
        }
    }

    private boolean hasAnyAuthorityName(String prefix, String... roles) {
        Set<String> roleSet = getAuthoritySet();

        for (String role : roles) {
            String defaultedRole = getRoleWithDefaultPrefix(prefix, role);
            assert roleSet != null;
            if (roleSet.contains(defaultedRole)) {
                return true;
            }
        }

        return false;
    }

    private Set<String> getAuthoritySet() {
        Authentication authentication = SecurityUtils.getAuthentication();
        if (null == authentication) {
            return null;
        }
        return AuthorityUtils.authorityListToSet(authentication.getAuthorities());
    }

    private static String getRoleWithDefaultPrefix(String prefix, String role) {
        if (role == null) {
            return role;
        }
        if (prefix == null || prefix.length() == 0) {
            return role;
        }
        if (role.startsWith(prefix)) {
            return role;
        }
        if (role.startsWith(DEFAULTROLEPREFIX)) {
            return role;
        }
        return prefix + ":" + role;
    }
}
