package com.hb0730.boot.admin.modules.sys.system.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.core.service.BaseServiceImpl;
import com.hb0730.boot.admin.modules.sys.system.event.RefreshOnlineUserRouteEvent;
import com.hb0730.boot.admin.modules.sys.system.mapper.SysRolePermissionMapper;
import com.hb0730.boot.admin.modules.sys.system.model.entity.SysRolePermission;
import jakarta.annotation.Nonnull;
import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/3/2
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysRolePermissionService extends BaseServiceImpl<SysRolePermissionMapper, SysRolePermission> {
    private final ApplicationContext applicationContext;

    /**
     * 获取角色对应的权限
     *
     * @param id 角色ID
     * @return 权限ID
     */
    public Optional<List<String>> findPermissionIdByRole(String id) {
//        LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
//                .eq(SysRolePermission::getRoleId, id);
//        return Optional.of(this.baseMapper.selectList(queryWrapper).stream().map(SysRolePermission::getPermissionId).collect(Collectors.toList()));
        return Optional.ofNullable(this.baseMapper.getPermissionIdByRoleId(id));
    }

    /**
     * 更新角色权
     *
     * @param id            id
     * @param permissionIds 权限集合，如果权限为空，则该角色没有权限,<b>只有最后一级</b>
     * @return .
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePermissionByRoles(@Nonnull String id, @Nullable List<SysRolePermission> permissionIds) {
        if (CollectionUtil.isEmpty(permissionIds)) {
            // 删除所有权限
            LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                    .eq(SysRolePermission::getRoleId, id);
            return this.baseMapper.delete(queryWrapper) > 0;
        }
        // 根据最后一级查找所有的父级
        List<String> permissionIdList = permissionIds.stream().map(SysRolePermission::getPermissionId).toList();
        Set<String> parentContainer = CollectionUtil.newHashSet();
        parentContainer = getParentId(permissionIdList, parentContainer);
        parentContainer.addAll(permissionIdList);
        parentContainer= parentContainer.stream().filter(ObjectUtil::isNotEmpty).collect(Collectors.toSet());
        // 删除所有权限
        LambdaQueryWrapper<SysRolePermission> queryWrapper = Wrappers.lambdaQuery(SysRolePermission.class)
                .eq(SysRolePermission::getRoleId, id);
        this.baseMapper.delete(queryWrapper);
        // 保存权限
        List<SysRolePermission> rolePermissions = parentContainer.stream().map(permissionId -> {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(id);
            rolePermission.setPermissionId(permissionId);
            rolePermission.setCreated(LocalDateTime.now());
            rolePermission.setCreatedBy(permissionIds.get(0).getCreatedBy());
            return rolePermission;
        }).toList();
        boolean success = this.saveBatch(rolePermissions);
        // 刷新在线用户权限
        applicationContext.publishEvent(new RefreshOnlineUserRouteEvent(this));
        return success;
    }

    private Set<String> getParentId(List<String> permissionIdList, Set<String> container) {
        List<String> parentIds = this.baseMapper.getParentIdByPermissionIds(permissionIdList);
        if (CollectionUtil.isNotEmpty(parentIds)) {
            container.addAll(parentIds);
            return getParentId(parentIds, container);
        }
        return container;
    }

}
