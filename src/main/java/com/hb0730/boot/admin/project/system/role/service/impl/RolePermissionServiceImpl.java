package com.hb0730.boot.admin.project.system.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.boot.admin.domain.service.impl.BaseServiceImpl;
import com.hb0730.boot.admin.exceptions.BusinessException;
import com.hb0730.boot.admin.project.system.permission.mapper.IPermissionMapper;
import com.hb0730.boot.admin.project.system.permission.model.entity.PermissionEntity;
import com.hb0730.boot.admin.project.system.role.mapper.IRolePermissionMapper;
import com.hb0730.boot.admin.project.system.role.model.entity.RolePermissionEntity;
import com.hb0730.boot.admin.project.system.role.service.IRolePermissionService;
import com.hb0730.commons.lang.collection.CollectionUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 角色权限  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends BaseServiceImpl<IRolePermissionMapper, RolePermissionEntity> implements IRolePermissionService {
    private final IPermissionMapper permissionMapper;

    @Override
    @Nullable
    public Map<Long, List<Long>> findPermissionIdByRoleId(@Nonnull Collection<Long> roleIds) {
        Assert.notEmpty(roleIds, "角色id为空");
        LambdaQueryWrapper<RolePermissionEntity> queryWrapper = Wrappers.lambdaQuery(RolePermissionEntity.class).in(RolePermissionEntity::getRoleId, roleIds);
        List<RolePermissionEntity> entities = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return null;
        }
        return entities.stream()
                .collect(
                        Collectors.groupingBy(
                                RolePermissionEntity::getRoleId,
                                Collectors.mapping(RolePermissionEntity::getPermissionId, Collectors.toList())
                        )
                );
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean savePermissionIdByRoleId(@Nonnull Long id, @Nonnull Collection<Long> permissionIds) {
        Assert.notNull(id, "角色id为空");
        Assert.notEmpty(permissionIds, "权限id为空");
        LambdaQueryWrapper<PermissionEntity> queryWrapper = Wrappers
                .lambdaQuery(PermissionEntity.class)
                .in(PermissionEntity::getId, permissionIds)
                .select(PermissionEntity::getId);
        List<PermissionEntity> entities = permissionMapper.selectList(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            throw new BusinessException("请传入正确的权限id");
        }
        Set<Long> ids = entities.parallelStream().map(PermissionEntity::getId).collect(Collectors.toSet());
        List<RolePermissionEntity> rolePermissionEntities = new ArrayList<>(ids.size());
        for (Long permissionId : ids) {
            RolePermissionEntity entity = new RolePermissionEntity();
            entity.setPermissionId(permissionId);
            entity.setRoleId(id);
            rolePermissionEntities.add(entity);
        }
        return super.saveBatch(rolePermissionEntities);
    }
}
