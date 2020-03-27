package com.hb0730.boot.admin.project.role.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.project.role.permission.mapper.ISystemRolePermissionMapper;
import com.hb0730.boot.admin.project.role.permission.model.entity.SystemRolePermissionEntity;
import com.hb0730.boot.admin.project.role.permission.service.ISystemRolePermissionService;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色权限  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-27
 */
@Service
public class SystemRolePermissionServiceImpl extends ServiceImpl<ISystemRolePermissionMapper, SystemRolePermissionEntity> implements ISystemRolePermissionService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePermissionByRoleId(@NonNull Long roleId, List<Long> permissionId) {
        if (CollectionUtils.isEmpty(permissionId)) {
            UpdateWrapper<SystemRolePermissionEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.set(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.NOT_USE);
            updateWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
            super.update(updateWrapper);

            QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
            return super.remove(queryWrapper);
        }
        // 启用状态
        Set<Long> usePermissionIds = getPermissionIdByRoleId(roleId, true);
        //未启用
        Set<Long> notUsePermissionIds = getPermissionIdByRoleId(roleId, false);
        //删除已启用的, 剩下未启用和新增
        permissionId.removeAll(usePermissionIds);
        List<Long> newPermissionId = new ArrayList<>(permissionId);
        // 获取未启用的交集
        permissionId.retainAll(notUsePermissionIds);
        if (!CollectionUtils.isEmpty(permissionId)) {
            updateState(roleId, permissionId);
        }
        // 删除未启用的 剩下新增
        newPermissionId.removeAll(permissionId);
        if (!CollectionUtils.isEmpty(newPermissionId)) {
            addNew(roleId,newPermissionId);
        }
        return true;
    }

    /**
     * 新增权限id
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return 是否成功
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addNew(@NonNull Long roleId, @NonNull List<Long> permissionIds) {
        List<SystemRolePermissionEntity> entities = Lists.newArrayList();
        permissionIds.forEach((permissionId) -> {
            SystemRolePermissionEntity entity = new SystemRolePermissionEntity();
            entity.setRoleId(roleId);
            entity.setPermissionId(permissionId);
            entity.setIsEnabled(SystemConstants.USE);
            entities.add(entity);
        });
        return super.saveBatch(entities);
    }

    /**
     * <p>
     * 修改状态
     * </p>
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    public void updateState(@NonNull Long roleId, @NonNull List<Long> permissionIds) {
        permissionIds.forEach((permissionId) -> {
            UpdateWrapper<SystemRolePermissionEntity> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
            updateWrapper.eq(SystemRolePermissionEntity.PERMISSION_ID, permissionId);
            updateWrapper.set(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.USE);
            super.update(updateWrapper);
        });

    }

    /**
     * <p>
     * 根据角色id获取权限id
     * </p>
     *
     * @param roleId 角色id
     * @param isUse  是否启用
     * @return 权限id
     */
    private Set<Long> getPermissionIdByRoleId(@NonNull Long roleId, Boolean isUse) {
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
        if (isUse) {
            queryWrapper.eq(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.USE);
        } else {
            queryWrapper.eq(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.NOT_USE);
        }
        List<SystemRolePermissionEntity> entities = super.list(queryWrapper);
        Set<Long> permissionIds = Sets.newConcurrentHashSet();
        if (!CollectionUtils.isEmpty(entities)) {
            permissionIds = entities.parallelStream().map(SystemRolePermissionEntity::getPermissionId).collect(Collectors.toSet());
        }
        return permissionIds;
    }
}
