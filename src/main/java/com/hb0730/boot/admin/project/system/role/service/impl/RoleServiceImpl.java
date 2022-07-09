package com.hb0730.boot.admin.project.system.role.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.domain.service.impl.SuperBaseServiceImpl;
import com.hb0730.boot.admin.event.role.RolePermissionEvent;
import com.hb0730.boot.admin.project.system.role.mapper.IRoleMapper;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleDTO;
import com.hb0730.boot.admin.project.system.role.model.dto.RoleExtDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleDeptEntity;
import com.hb0730.boot.admin.project.system.role.model.entity.RoleEntity;
import com.hb0730.boot.admin.project.system.role.model.entity.RolePermissionEntity;
import com.hb0730.boot.admin.project.system.role.model.query.RoleParams;
import com.hb0730.boot.admin.project.system.role.service.IRoleDeptService;
import com.hb0730.boot.admin.project.system.role.service.IRolePermissionService;
import com.hb0730.boot.admin.project.system.role.service.IRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.annotation.Nonnull;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 角色  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends SuperBaseServiceImpl<Long, RoleParams, RoleExtDTO, RoleEntity, IRoleMapper> implements IRoleService {

    private final IRoleDeptService roleDeptService;
    private final IRolePermissionService rolePermissionService;
    private final ApplicationEventPublisher eventPublisher;

    @Override
    public Page<RoleExtDTO> page(@Nonnull RoleParams params) {
        Page<RoleExtDTO> page = super.page(params);
        List<RoleExtDTO> records = page.getRecords();
        if (!CollectionUtil.isEmpty(records)) {
            fillDepts(records);
            filePermission(records);
        }
        return page;
    }

    @Override
    public List<RoleExtDTO> list(@Nonnull RoleParams params) {
        List<RoleExtDTO> list = super.list(params);
        if (!CollectionUtil.isEmpty(list)) {
            fillDepts(list);
            filePermission(list);
        }
        return list;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@Nonnull RoleExtDTO dto) {
        RoleEntity entity = dto.convertTo();
        boolean result = super.save(entity);
        Long id = entity.getId();
        List<Long> deptIds = dto.getDeptIds();
        if (!CollectionUtil.isEmpty(deptIds) && Objects.nonNull(id)) {
            roleDeptService.saveRoleDepts(id, deptIds);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateById(@Nonnull Long id, @Nonnull RoleExtDTO dto) {
        RoleEntity entity = dto.convertTo();
        entity.setId(id);
        boolean result = this.updateById(entity);
        updateRoleDepts(id, dto.getDeptIds());

        return result;
    }

    @Override
    public boolean updateById(RoleEntity entity) {
        RoleEntity e1 = super.getById(entity);
        BeanUtil.copyProperties(entity, e1);
        return super.updateById(e1);
    }

    @Override
    public QueryWrapper<RoleEntity> query(@Nonnull RoleParams params) {
        QueryWrapper<RoleEntity> query = QueryWrapperUtils.getQuery(params);
        if (StrUtil.isNotBlank(params.getName())) {
            query.like(RoleEntity.NAME, params.getName());
        }
        if (StrUtil.isNotBlank(params.getCode())) {
            query.eq(RoleEntity.CODE, params.getCode());
        }
        if (Objects.nonNull(params.getIsEnabled())) {
            query.eq(RoleEntity.IS_ENABLED, params.getIsEnabled());
        }
        return query;
    }

    private void fillDepts(List<RoleExtDTO> roleDeptList) {
        if (CollectionUtil.isEmpty(roleDeptList)) {
            return;
        }
        List<Long> ids = roleDeptList.parallelStream().map(RoleDTO::getId).collect(Collectors.toList());
        Map<Long, List<Long>> map = roleDeptService.findDeptIdByRoleIds(ids);
        if (CollectionUtil.isEmpty(map)) {
            return;
        }
        roleDeptList.forEach(e -> e.setDeptIds(map.get(e.getId())));
    }

    private void filePermission(List<RoleExtDTO> roleList) {
        if (CollectionUtil.isEmpty(roleList)) {
            return;
        }
        List<Long> ids = roleList.parallelStream().map(RoleDTO::getId).collect(Collectors.toList());
        Map<Long, List<Long>> map = rolePermissionService.findPermissionIdByRoleId(ids);
        if (CollectionUtil.isEmpty(map)) {
            return;
        }
        roleList.forEach(e -> e.setPermissionIds(map.get(e.getId())));
    }

    private void updateRoleDepts(@Nonnull Long id, List<Long> roleRoleIds) {
        Assert.notNull(id, "角色id不为空");
        LambdaQueryWrapper<RoleDeptEntity> queryWrapper = Wrappers.lambdaQuery(RoleDeptEntity.class).eq(RoleDeptEntity::getRoleId, id);
        // 数据范围为空-本级
        if (CollectionUtil.isEmpty(roleRoleIds)) {
            roleDeptService.remove(queryWrapper);
            return;
        }
        List<RoleDeptEntity> entities = roleDeptService.list(queryWrapper);
        // 全新 -新增
        if (CollectionUtil.isEmpty(entities)) {
            roleDeptService.saveRoleDepts(id, roleRoleIds);
            return;
        }
        // 旧数据
        Set<Long> oldDeptIds = entities.parallelStream().map(RoleDeptEntity::getDeptId).collect(Collectors.toSet());

        Set<Long> oldDeptIds2 = Sets.newHashSet(oldDeptIds);
        Set<Long> newDeptIds = Sets.newHashSet(roleRoleIds);
        // 已过期
        // 原始数据-新数据=已过期数据
        oldDeptIds.removeAll(newDeptIds);
        if (!CollectionUtil.isEmpty(oldDeptIds)) {
            LambdaQueryWrapper<RoleDeptEntity> query = Wrappers.lambdaQuery(RoleDeptEntity.class).eq(RoleDeptEntity::getRoleId, id).in(RoleDeptEntity::getDeptId, oldDeptIds);
            roleDeptService.remove(query);
        }
        // 新增
        // 新数据-原始数据=新增数据
        newDeptIds.removeAll(oldDeptIds2);
        if (!CollectionUtil.isEmpty(newDeptIds)) {
            roleDeptService.saveRoleDepts(id, newDeptIds);
        }
        //未变 交集
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateRolePermission(@Nonnull Long id, List<Long> permissionIds) {
        Assert.notNull(id, "角色id不为空");
        LambdaQueryWrapper<RolePermissionEntity> queryWrapper = Wrappers
            .lambdaQuery(RolePermissionEntity.class)
            .eq(RolePermissionEntity::getRoleId, id);
        // 角色 没有权限
        if (CollectionUtil.isEmpty(permissionIds)) {
            rolePermissionService.remove(queryWrapper);
            return true;
        }
        List<RolePermissionEntity> permissionEntities = rolePermissionService.list(queryWrapper);
        // 权限
        if (CollectionUtil.isEmpty(permissionEntities)) {
            rolePermissionService.savePermissionIdByRoleId(id, permissionIds);
            return true;
        }
        // 旧的
        Set<Long> oldPermissionIds = permissionEntities
            .parallelStream()
            .map(RolePermissionEntity::getPermissionId).collect(Collectors.toSet());
        Set<Long> oldPermissionIds2 = Sets.newHashSet(oldPermissionIds);
        //  新
        Set<Long> newPermissionIds = Sets.newHashSet(permissionIds);

        // 旧数据-新数据=已过期数据
        oldPermissionIds.removeAll(newPermissionIds);
        if (!CollectionUtil.isEmpty(oldPermissionIds)) {
            LambdaQueryWrapper<RolePermissionEntity> query = Wrappers
                .lambdaQuery(RolePermissionEntity.class).eq(RolePermissionEntity::getRoleId, id).in(RolePermissionEntity::getPermissionId, oldPermissionIds);
            rolePermissionService.remove(query);
        }

        //新数据-旧数据=新增数据

        newPermissionIds.removeAll(oldPermissionIds2);
        if (!CollectionUtil.isEmpty(newPermissionIds)) {
            rolePermissionService.savePermissionIdByRoleId(id, newPermissionIds);
        }
        eventPublisher.publishEvent(new RolePermissionEvent(this, id));
        return true;
    }

    @Override
    public List<RoleEntity> findEnabledRoleByIds(@Nonnull Collection<Long> ids) {
        Assert.notNull(ids, "角色id不为空");
        LambdaQueryWrapper<RoleEntity> queryWrapper = Wrappers
            .lambdaQuery(RoleEntity.class)
            .in(RoleEntity::getId, ids)
            .select(RoleEntity::getId, RoleEntity::getCode);
        return super.list(queryWrapper);
    }
}
