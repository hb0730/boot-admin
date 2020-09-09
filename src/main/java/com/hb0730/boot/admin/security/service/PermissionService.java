package com.hb0730.boot.admin.security.service;

import com.hb0730.boot.admin.annotation.PreAuth;
import com.hb0730.boot.admin.security.utils.SecurityUtils;
import com.hb0730.commons.lang.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * 自定义权限认证，(尽量不要用此方法，使用标准的security认证),
 * 参考自<a href="https://github.com/yangzongzhuan/RuoYi-Vue">若依</a>
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service("bootAdmin")
public class PermissionService {
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
        if (StringUtils.isBlank(value)) {
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
