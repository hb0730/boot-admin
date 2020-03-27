package com.hb0730.boot.admin.project.role.permission.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.role.permission.model.entity.SystemRolePermissionEntity;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * <p>
 * 角色权限  服务类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
public interface ISystemRolePermissionService extends IService<SystemRolePermissionEntity> {
    /**
     * <p>
     * 保存(修改)角色对应的权限id
     * </p>
     *
     * @param roleId       菜单id
     * @param permissionId 权限id
     * @return 是否成功
     */
    boolean savePermissionByRoleId(@NonNull Long roleId, List<Long> permissionId);
}
