package com.hb0730.sys.service;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.hb0730.base.exception.ServiceException;
import com.hb0730.base.utils.CollectionUtil;
import com.hb0730.mybatis.core.service.BaseService;
import com.hb0730.query.mybatis.plus.QueryHelper;
import com.hb0730.sys.domain.dto.PermissionDto;
import com.hb0730.sys.domain.dto.PermissionSaveDto;
import com.hb0730.sys.domain.entity.SysPermission;
import com.hb0730.sys.domain.entity.SysRole;
import com.hb0730.sys.domain.query.PermissionQuery;
import com.hb0730.sys.mapper.SysPermissionMapper;
import com.hb0730.sys.service.mapstruct.SysPermissionMapstruct;
import com.hb0730.sys.service.mapstruct.SysPermissionSaveMapstruct;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/28
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SysPermissionService extends BaseService<SysPermissionMapper, SysPermission> {

    @Lazy
    @Resource
    private SysRoleService sysRoleService;
    private final SysPermissionMapstruct mapstruct;
    private final SysPermissionSaveMapstruct saveMapstruct;

    /**
     * 用户路由
     *
     * @param userId .
     * @return .
     */
    public List<PermissionDto> userRoutes(String userId) {
        List<SysRole> roles = sysRoleService.listByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, SysRole::getId);
        List<SysPermission> sysPermissions = listByRoleIds(roleIds);
        return mapstruct.toDtoList(sysPermissions);
    }

    /**
     * 根据用户id查询权限
     *
     * @param userId 用户id
     * @return 权限
     */
    public List<SysPermission> listByUserId(String userId) {
        List<SysRole> roles = sysRoleService.listByUserId(userId);
        if (CollectionUtil.isEmpty(roles)) {
            return null;
        }
        List<String> roleIds = CollectionUtil.listToList(roles, SysRole::getId);
        return listByRoleIds(roleIds);
    }

    /**
     * 根据角色id查询权限
     *
     * @param roleIds 角色id
     * @return 权限
     */
    public List<SysPermission> listByRoleIds(List<String> roleIds) {
        return baseMapper.listByRoleIds(roleIds);
    }

    /**
     * 根据角色id查询权限
     *
     * @param roleId 角色id
     * @return 权限
     */
    public List<SysPermission> listByRoleId(String roleId) {
        return baseMapper.listByRoleIds(Collections.singletonList(roleId));
    }


    /**
     * 查询
     *
     * @param query .
     * @return .
     */
    public List<PermissionDto> list(PermissionQuery query) {
        QueryWrapper<SysPermission> queryWrapper = QueryHelper.ofBean(query);
        queryWrapper = QueryHelper.order(queryWrapper, query);
        List<SysPermission> list = list(queryWrapper);
        return mapstruct.toDtoList(list);
    }

    /**
     * 查询已启用的菜单与权限
     *
     * @return .
     */
    public List<PermissionDto> findAllEnabled() {
        LambdaQueryWrapper<SysPermission> queryWrapper = Wrappers
                .lambdaQuery(SysPermission.class)
                .eq(SysPermission::getEnabled, true)
                .orderByAsc(SysPermission::getRank);
        List<SysPermission> list = list(queryWrapper);
        return mapstruct.toDtoList(list);
    }

    /**
     * 保存
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void save(PermissionSaveDto dto) {
        SysPermission entity = saveMapstruct.toEntity(dto);
        save(entity);
    }

    /**
     * 更新
     *
     * @param dto .
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateById(PermissionSaveDto dto) {
        SysPermission saveData = saveMapstruct.toEntity(dto);
        if (null == saveData.getId()) {
            throw new ServiceException("id不能为空");
        }
        if (dto.getId().equals(dto.getParentId())) {
            throw new ServiceException("上级不能是自己");
        }
        SysPermission entity = getById(dto.getId());
        // 保证相关数据不会被修改
        BeanUtil.copyProperties(saveData, entity, CopyOptions.create().ignoreNullValue());
        updateById(entity);
    }

    /**
     * 删除
     *
     * @param id .
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteById(String id) {
        boolean hasChild = baseMapper.hasChild(id);
        if (hasChild) {
            throw new ServiceException("请先删除子节点");
        }
        // 与角色已绑定的权限不能删除
        boolean existsed = baseMapper.existsRolePermission(id);
        if (existsed) {
            throw new ServiceException("已绑定角色，不能删除");
        }

        removeById(id);
    }
}
