package com.hb0730.sys.tenant.service;

import com.hb0730.sys.tenant.domain.TenantRole;
import com.hb0730.sys.tenant.domain.TenantRolePermission;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
public interface ITenantRoleService {

    /**
     * 根据角色ID查询角色与权限的映射关系
     *
     * @param roleIds 角色id
     * @return 权限id
     */
    List<TenantRolePermission> findRolePermissionByRoleIdIn(Collection<String> roleIds);

    /**
     * 根据组织id查询系统角色id
     *
     * @param orgIds 组织id
     * @return 系统角色id
     */
    Set<String> getSystemRoleIdsByOrgIds(Collection<String> orgIds);

    /**
     * 根据角色id查询角色
     *
     * @param roleId 角色id
     * @return 角色
     */
    Optional<TenantRole> findById(String roleId);


    /**
     * 保存角色权限
     *
     * @param tenantRole 角色
     */
    void saveRolePermission(TenantRole tenantRole);
}
