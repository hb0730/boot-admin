package com.hb0730.sys.system.service;

import com.hb0730.core.service.BasicService;
import com.hb0730.sys.system.model.entity.SysRolePermission;
import com.hb0730.sys.system.repository.SysRolePermissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/8
 */
@Service
@RequiredArgsConstructor
public class SysRolePermissionService extends BasicService<String, SysRolePermission, SysRolePermissionRepository> {

    /**
     * 删除角色权限
     *
     * @param roleId 角色id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByRoleId(String roleId) {
        return repository.removeByRoleId(roleId);
    }

    /**
     * 获取角色权限
     *
     * @param roleId 角色id
     * @return 权限
     */
    public List<String> getPermsByRoleId(String roleId) {
        Optional<List<SysRolePermission>> permissions = repository.listByRoleId(roleId);
        return permissions
                .map(sysRolePermissions -> sysRolePermissions.stream()
                        .map(SysRolePermission::getPermissionId)
                        .toList()).orElse(List.of());

    }

    /**
     * 获取角色权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    public List<String> getPermsByRoleIds(List<String> roleIds) {
        Optional<List<SysRolePermission>> permissions = repository.listByRoleIds(roleIds);
        return permissions
                .map(sysRolePermissions -> sysRolePermissions.stream()
                        .map(SysRolePermission::getPermissionId)
                        .toList()).orElse(List.of());

    }
}
