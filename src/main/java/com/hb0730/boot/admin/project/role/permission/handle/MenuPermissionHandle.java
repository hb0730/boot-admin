package com.hb0730.boot.admin.project.role.permission.handle;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.project.menu.permission.model.entity.SystemMenuPermissionEntity;
import com.hb0730.boot.admin.project.menu.permission.service.ISystemMenuPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class MenuPermissionHandle {
    @Autowired
    private ISystemMenuPermissionService systemMenuPermissionService;

    /**
     * <p>
     * 根据权限id获取key-value方式的菜单权限
     * </p>
     *
     * @param ids 权限id
     * @return key-value方式的菜单权限
     */
    public Map<Long, Set<Long>> getMenuPermissionIdsByPermissionIds(Collection<Long> ids) {
        List<SystemMenuPermissionEntity> menuPermissions = systemMenuPermissionService.getMenuPermissionByPermissionIds(ids);
        if (CollectionUtils.isEmpty(menuPermissions)) {
            return Maps.newHashMap();
        }
        Map<Long, Set<Long>> maps = Maps.newHashMapWithExpectedSize(menuPermissions.size());
        menuPermissions.forEach((menuPermission) -> {
            Long menuId = menuPermission.getMenuId();
            Set<Long> permissionIds = maps.get(menuId);
            if (CollectionUtils.isEmpty(permissionIds)) {
                Set<Long> permission = Sets.newHashSet();
                permission.add(menuPermission.getPermissionId());
                maps.put(menuId, permission);
            } else {
                permissionIds.add(menuPermission.getPermissionId());
                maps.put(menuId, permissionIds);
            }
        });
        return maps;
    }
}
