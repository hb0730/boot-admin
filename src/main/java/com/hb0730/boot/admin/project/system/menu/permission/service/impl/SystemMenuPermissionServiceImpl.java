package com.hb0730.boot.admin.project.system.menu.permission.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.commons.domain.service.SuperBaseService;
import com.hb0730.boot.admin.commons.utils.PageUtils;
import com.hb0730.boot.admin.commons.utils.QueryWrapperUtils;
import com.hb0730.boot.admin.commons.utils.bean.BeanUtils;
import com.hb0730.boot.admin.exception.BaseException;
import com.hb0730.boot.admin.project.system.menu.permission.handler.PermissionHandle;
import com.hb0730.boot.admin.project.system.menu.permission.mapper.ISystemMenuPermissionMapper;
import com.hb0730.boot.admin.project.system.menu.permission.model.entity.SystemMenuPermissionEntity;
import com.hb0730.boot.admin.project.system.menu.permission.model.vo.PermissionParams;
import com.hb0730.boot.admin.project.system.menu.permission.service.ISystemMenuPermissionService;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.SystemPermissionEntity;
import com.hb0730.boot.admin.project.system.permission.model.vo.SystemPermissionVO;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单权限  服务实现类
 * </p>
 *
 * @author bing_huang
 * @since 2020-03-26
 */
@Service
public class SystemMenuPermissionServiceImpl extends SuperBaseService<PermissionParams, SystemPermissionVO, ISystemMenuPermissionMapper, SystemMenuPermissionEntity> implements ISystemMenuPermissionService {
    @Autowired
    private PermissionHandle permissionHandle;

    @Override
    public List<SystemPermissionVO> getPermissionByMenuId(@NonNull Long menuId) {
        QueryWrapper<SystemMenuPermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuPermissionEntity.MENU_ID, menuId);
        List<SystemMenuPermissionEntity> permissionEntities = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(permissionEntities)) {
            return Lists.newArrayList();
        }
        Set<Long> permissionIds = permissionEntities.parallelStream().map(SystemMenuPermissionEntity::getPermissionId).collect(Collectors.toSet());
        List<SystemPermissionDTO> permissions = permissionHandle.getPermissionByIds(permissionIds);
        return BeanUtils.transformFromInBatch(permissions, SystemPermissionVO.class);
    }

    @Override
    public Page<SystemPermissionVO> getPermissionByMenuId(@NonNull Long menuId, PermissionParams params) {
        QueryWrapper<SystemMenuPermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuPermissionEntity.MENU_ID, menuId);
        Page<SystemMenuPermissionEntity> page = QueryWrapperUtils.getPage(params);
        page = super.page(page, queryWrapper);
        if (CollectionUtils.isEmpty(page.getRecords())) {
            return PageUtils.toBean(page, SystemPermissionVO.class);
        }
        List<SystemMenuPermissionEntity> list = page.getRecords();
        Set<Long> permissionIds = list.parallelStream().map(SystemMenuPermissionEntity::getPermissionId).collect(Collectors.toSet());
        List<SystemPermissionDTO> permissionByIds = permissionHandle.getPermissionByIds(permissionIds, params);
        Page<SystemPermissionDTO> pagePermission = PageUtils.replacePageList(page, permissionByIds);
        return PageUtils.toBean(pagePermission, SystemPermissionVO.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(@NonNull Long menuId, SystemPermissionVO permissionInfo) {
        SystemPermissionEntity entity = BeanUtils.transformFrom(permissionInfo, SystemPermissionEntity.class);
        permissionHandle.savePermission(entity);
        assert entity != null;
        Long permissionId = entity.getId();
        if (Objects.isNull(permissionId)) {
            throw new BaseException("保存权限失败,请重试");
        }
        SystemMenuPermissionEntity menuPermissionEntity = new SystemMenuPermissionEntity();
        menuPermissionEntity.setDelFlag(0);
        menuPermissionEntity.setMenuId(menuId);
        menuPermissionEntity.setPermissionId(permissionId);
        return save(menuPermissionEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updatePermissionById(@NonNull Long permissionId, SystemPermissionVO permissionVO) {
        permissionVO.setId(permissionId);
        SystemPermissionEntity entity = BeanUtils.transformFrom(permissionVO, SystemPermissionEntity.class);
        return permissionHandle.updateById(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteByPermissionId(@NonNull Long permissionId) {
        SystemPermissionEntity entity = new SystemPermissionEntity();
        entity.setId(permissionId);
        permissionHandle.updateById(entity);
        return permissionHandle.deleteById(permissionId);
    }

    @Override
    public Map<Long, Set<Long>> getPermissionIdByMenuId() {
        QueryWrapper<SystemMenuPermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuPermissionEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.groupBy(SystemMenuPermissionEntity.MENU_ID);
        List<SystemMenuPermissionEntity> entities = super.list(queryWrapper);
        if (CollectionUtils.isEmpty(entities)) {
            return Maps.newHashMap();
        }
        Map<Long, Set<Long>> maps = Maps.newHashMap();
        entities.forEach((entity) -> {
            Long menuId = entity.getMenuId();
            QueryWrapper<SystemMenuPermissionEntity> q1 = new QueryWrapper<>();
            q1.eq(SystemMenuPermissionEntity.MENU_ID, menuId);
            q1.eq(SystemMenuPermissionEntity.IS_ENABLED, SystemConstants.ENABLED);
            q1.select(SystemMenuPermissionEntity.PERMISSION_ID);
            List<SystemMenuPermissionEntity> permissionEntities = super.list(q1);
            if (!CollectionUtils.isEmpty(permissionEntities)) {
                Set<Long> permissionIds = permissionEntities.parallelStream().map(SystemMenuPermissionEntity::getPermissionId).collect(Collectors.toSet());
                maps.put(menuId, permissionIds);
            }
        });
        return maps;
    }

    @Override
    public List<SystemMenuPermissionEntity> getMenuPermissionByPermissionIds(Collection<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Lists.newArrayList();
        }
        QueryWrapper<SystemMenuPermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemMenuPermissionEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.in(SystemMenuPermissionEntity.PERMISSION_ID, permissionIds);
        queryWrapper.select(SystemMenuPermissionEntity.PERMISSION_ID, SystemMenuPermissionEntity.MENU_ID);
        return super.list(queryWrapper);
    }

    @Override
    @Deprecated
    public Page<SystemPermissionVO> page(@NotNull @NonNull PermissionParams params) {
        return null;
    }

    @Override
    @Deprecated
    public List<SystemPermissionVO> list(@NonNull PermissionParams params) {
        return null;
    }

    @NotNull
    @NonNull
    @Override
    public QueryWrapper<SystemMenuPermissionEntity> query(@NonNull PermissionParams params) {
        QueryWrapper<SystemMenuPermissionEntity> query = QueryWrapperUtils.getQuery(params);

        return query;
    }
}
