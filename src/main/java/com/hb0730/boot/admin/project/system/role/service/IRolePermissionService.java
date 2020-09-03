package com.hb0730.boot.admin.project.system.role.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.system.role.model.entity.RolePermissionEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 角色权限  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IRolePermissionService extends IService<RolePermissionEntity> {

    /**
     * 根据角色查询权限
     *
     * @param roleIds 角色id
     * @return 权限id
     */
    @Nullable
    Map<Long, List<Long>> findPermissionIdByRoleId(@Nonnull Collection<Long> roleIds);

    /**
     * 新增角色分配的权限
     *
     * @param id            角色id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    boolean savePermissionIdByRoleId(@Nonnull Long id, @Nonnull Collection<Long> permissionIds);
}
