package com.hb0730.boot.admin.project.system.user.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.utils.bean.BeanUtils;
import com.hb0730.boot.admin.project.system.permission.model.dto.SystemPermissionDTO;
import com.hb0730.boot.admin.project.system.permission.model.entity.SystemPermissionEntity;
import com.hb0730.boot.admin.project.system.permission.service.ISystemPermissionService;
import com.hb0730.boot.admin.project.system.post.model.dto.SystemPostDTO;
import com.hb0730.boot.admin.project.system.post.model.entity.SystemPostEntity;
import com.hb0730.boot.admin.project.system.post.service.ISystemPostService;
import com.hb0730.boot.admin.project.system.role.model.dto.SystemRoleDTO;
import com.hb0730.boot.admin.project.system.role.model.entity.SystemRoleEntity;
import com.hb0730.boot.admin.project.system.role.permission.model.entity.SystemRolePermissionEntity;
import com.hb0730.boot.admin.project.system.role.permission.service.ISystemRolePermissionService;
import com.hb0730.boot.admin.project.system.role.service.ISystemRoleService;
import com.hb0730.boot.admin.project.system.user.model.dto.LoginUserDTO;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色岗位权限信息
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class RolePostPermissionHandle {

    private ISystemRoleService systemRoleService;
    private ISystemPostService systemPostService;
    private ISystemPermissionService systemPermissionService;
    private UserRolePostHandle userRolePostHandle;
    private ISystemRolePermissionService systemRolePermissionService;

    public RolePostPermissionHandle(ISystemRoleService systemRoleService, ISystemPostService systemPostService, ISystemPermissionService systemPermissionService, UserRolePostHandle userRolePostHandle, ISystemRolePermissionService systemRolePermissionService) {
        this.systemRoleService = systemRoleService;
        this.systemPostService = systemPostService;
        this.systemPermissionService = systemPermissionService;
        this.userRolePostHandle = userRolePostHandle;
        this.systemRolePermissionService = systemRolePermissionService;
    }

    public ISystemRoleService getSystemRoleService() {
        return systemRoleService;
    }

    public ISystemPostService getSystemPostService() {
        return systemPostService;
    }

    public ISystemPermissionService getSystemPermissionService() {
        return systemPermissionService;
    }

    public UserRolePostHandle getUserRolePostHandle() {
        return userRolePostHandle;
    }

    public ISystemRolePermissionService getSystemRolePermissionService() {
        return systemRolePermissionService;
    }

    /**
     * <p>
     * 根据角色id获取角色信息
     * </p>
     *
     * @param roleIds 角色id
     * @return 角色信息
     */
    public List<SystemRoleDTO> getRoleByIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        QueryWrapper<SystemRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRoleEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.in(SystemRoleEntity.ID, roleIds);
        List<SystemRoleEntity> entities = systemRoleService.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemRoleDTO.class);
    }

    /**
     * 根据岗位id获取岗位信息
     *
     * @param postIds 岗位id
     * @return 岗位信息
     */
    public List<SystemPostDTO> getPostByIds(List<Long> postIds) {
        if (CollectionUtils.isEmpty(postIds)) {
            return Lists.newArrayList();
        }
        QueryWrapper<SystemPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemPostEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.in(SystemPostEntity.ID, postIds);
        List<SystemPostEntity> entities = systemPostService.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemPostDTO.class);
    }

    /**
     * 根据角色id获取权限id
     *
     * @param roleIds 角色id
     * @return 权限id
     */
    public List<Long> getPermissionIdsByRoleIds(List<Long> roleIds) {
        if (CollectionUtils.isEmpty(roleIds)) {
            return Lists.newArrayList();
        }
        QueryWrapper<SystemRolePermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemRolePermissionEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.in(SystemRolePermissionEntity.ROLE_ID, roleIds);
        List<SystemRolePermissionEntity> list = systemRolePermissionService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.parallelStream().map(SystemRolePermissionEntity::getPermissionId).collect(Collectors.toList());
    }

    /**
     * <p>
     * 根据权限id获取权限信息
     * </p>
     *
     * @param permissionIds 权限id
     * @return 权限信息
     */
    public List<SystemPermissionDTO> getPermissionByIds(List<Long> permissionIds) {
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Lists.newArrayList();
        }
        QueryWrapper<SystemPermissionEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemPermissionEntity.IS_ENABLED, SystemConstants.ENABLED);
        queryWrapper.in(SystemPermissionEntity.ID, permissionIds);
        List<SystemPermissionEntity> entities = systemPermissionService.list(queryWrapper);
        return BeanUtils.transformFromInBatch(entities, SystemPermissionDTO.class);
    }

    /**
     * <p>
     * 根据登录用户信息获取相关信息
     * </p>
     *
     * @param userDTO 登录用户
     */
    public void getRolePostPermissionByLoginUser(@NonNull LoginUserDTO userDTO) {
        Long id = userDTO.getId();
        List<Long> postIds = userRolePostHandle.getPostIdByUserId(id);
        userDTO.setPosts(getPostByIds(postIds));
        List<Long> roleIds = userRolePostHandle.getRoleIdByUserId(id);
        userDTO.setRoles(getRoleByIds(roleIds));
        List<Long> permissionIds = getPermissionIdsByRoleIds(roleIds);
        userDTO.setPermissions(getPermissionByIds(permissionIds));
    }
}
