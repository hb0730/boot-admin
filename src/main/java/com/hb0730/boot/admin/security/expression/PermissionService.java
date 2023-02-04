package com.hb0730.boot.admin.security.expression;

import com.hb0730.boot.admin.security.model.Authority;
import com.hb0730.boot.admin.security.util.SecurityUtil;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/2/4
 */
@Service("permission")
public class PermissionService {
    private final GrantedAuthorityDefaults grantedAuthorityDefaults;

    public PermissionService(ObjectProvider<GrantedAuthorityDefaults> provider) {
        this.grantedAuthorityDefaults = provider.getIfAvailable();
    }

    /**
     * 是否有对应的权限或者角色
     *
     * @param anyPermission 权限或者角色
     * @return 是否授权
     */
    public boolean hashPermission(String anyPermission) {
        Collection<Authority> authorities = SecurityUtil.getCurrentPermission();
        List<String> permission = authorities.stream().map(Authority::getAuthority).toList();
        String role = null;
        if (null != this.grantedAuthorityDefaults) {
            role = getRoleWithDefaultPrefix(this.grantedAuthorityDefaults.getRolePrefix(), anyPermission);
        }
        return permission.contains(role);
    }

    /**
     * 是否有对应的权限或者角色
     *
     * @param anyPermission 权限或者角色
     * @return 是否授权
     */
    public boolean hashPermission(String... anyPermission) {
        for (String permission : anyPermission) {
            if (hashPermission(permission)) {

                return true;
            }
        }
        return false;
    }

    private static String getRoleWithDefaultPrefix(String defaultRolePrefix, String role) {
        if (role == null) {
            return role;
        }
        if (defaultRolePrefix == null || defaultRolePrefix.length() == 0) {
            return role;
        }
        if (role.startsWith(defaultRolePrefix)) {
            return role;
        }
        return defaultRolePrefix + role;
    }
}
