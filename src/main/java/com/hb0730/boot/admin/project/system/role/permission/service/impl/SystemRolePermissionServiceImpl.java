package com.hb0730.boot.admin.project.system.role.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.BaseServiceImpl;
import com.hb0730.boot.admin.project.system.role.permission.handle.MenuPermissionHandle;
import com.hb0730.boot.admin.project.system.role.permission.mapper.ISystemRolePermissionMapper;
import com.hb0730.boot.admin.project.system.role.permission.model.entity.SystemRolePermissionEntity;
import com.hb0730.boot.admin.project.system.role.permission.service.ISystemRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;
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
public class SystemRolePermissionServiceImpl extends BaseServiceImpl<ISystemRolePermissionMapper, SystemRolePermissionEntity> implements ISystemRolePermissionService {
    @Autowired
    private MenuPermissionHandle menuPermissionHandle;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePermissionByRoleId(@NonNull Long roleId, List<Long> permissionIds) {
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
        List<SystemRolePermissionEntity> entities = super.list(queryWrapper);
        // 新增
        if (CollectionUtils.isEmpty(entities)) {
            if (CollectionUtils.isEmpty(permissionIds)) {
                return true;
            }
            return addNew(roleId, permissionIds);
        }
        List<Long> list = entities.parallelStream().map(SystemRolePermissionEntity::getPermissionId).collect(Collectors.toList());
        List<Long> updateList = Lists.newArrayList();
        List<Long> saveList = Lists.newArrayList();
        permissionIds.forEach((orgId) -> {
            if (list.contains(orgId)) {
                updateList.add(orgId);
            } else {
                saveList.add(orgId);
            }
        });
        //多余的
        list.removeAll(permissionIds);
        saveOrUpdate(roleId, updateList);
        saveOrUpdate(roleId, saveList);
        updateState(roleId, list, SystemConstants.NOT_USE);
        return true;
    }

    @Override
    public Map<Long, Set<Long>> getPermissionIdsByRoleId(@NonNull Long roleId) {
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.USE);
        queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
        queryWrapper.select(SystemRolePermissionEntity.PERMISSION_ID);
        List<SystemRolePermissionEntity> entities = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return Maps.newHashMap();
        }
        Set<Long> permissionIds = entities.parallelStream().map(SystemRolePermissionEntity::getPermissionId).collect(Collectors.toSet());
        return menuPermissionHandle.getMenuPermissionIdsByPermissionIds(permissionIds);
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
        if (!CollectionUtils.isEmpty(permissionIds)) {
            permissionIds.forEach((permissionId) -> {
                SystemRolePermissionEntity entity = new SystemRolePermissionEntity();
                entity.setRoleId(roleId);
                entity.setPermissionId(permissionId);
                entity.setIsEnabled(SystemConstants.USE);
                entities.add(entity);
            });
        }
        if (!CollectionUtils.isEmpty(entities)) {
            super.saveBatch(entities);
        }
        return true;
    }

    /**
     * <p>
     * 新增获取修改
     * </p>
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     * @return 是否成
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean saveOrUpdate(@NonNull Long roleId, @NonNull List<Long> permissionIds) {
        List<Long> savePermission = Lists.newArrayList();
        List<Long> updatePermission = Lists.newArrayList();
        permissionIds.forEach((permissionId) -> {
            QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
            queryWrapper.eq(SystemRolePermissionEntity.PERMISSION_ID, permissionId);
            List<SystemRolePermissionEntity> list = super.list(queryWrapper);
            if (!CollectionUtils.isEmpty(list)) {
                updatePermission.add(permissionId);
            } else {
                savePermission.add(permissionId);
            }
        });
        if (!CollectionUtils.isEmpty(savePermission)) {
            addNew(roleId, savePermission);
        }
        if (!CollectionUtils.isEmpty(updatePermission)) {
            updateState(roleId, updatePermission, SystemConstants.USE);
        }
        return true;
    }

    /**
     * <p>
     * 修改状态
     * </p>
     *
     * @param roleId        角色id
     * @param permissionIds 权限id
     */
    public void updateState(@NonNull Long roleId, @NonNull List<Long> permissionIds, Integer isUse) {
        List<SystemRolePermissionEntity> entities = Lists.newArrayList();
        permissionIds.forEach((permissionId) -> {
            QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq(SystemRolePermissionEntity.ROLE_ID, roleId);
            queryWrapper.eq(SystemRolePermissionEntity.PERMISSION_ID, permissionId);
            SystemRolePermissionEntity entity = super.getOne(queryWrapper);
            if (!Objects.isNull(entity)) {
                entity.setIsEnabled(isUse == null ? SystemConstants.USE : isUse);
                entities.add(entity);
            }
        });
        if (!CollectionUtils.isEmpty(entities)) {
            super.updateBatchById(entities);
        }

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
