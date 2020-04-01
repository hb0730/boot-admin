package com.hb0730.boot.admin.project.system.user.handle;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Lists;
import com.hb0730.boot.admin.commons.constant.SystemConstants;
import com.hb0730.boot.admin.project.system.user.post.model.entity.SystemUserPostEntity;
import com.hb0730.boot.admin.project.system.user.post.service.ISystemUserPostService;
import com.hb0730.boot.admin.project.system.user.role.model.entity.SystemUserRoleEntity;
import com.hb0730.boot.admin.project.system.user.role.service.ISystemUserRoleService;
import com.hb0730.boot.admin.project.system.user.model.vo.UserVO;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@Component
public class UserRolePostHandle {
    private ISystemUserRoleService systemUserRoleService;
    private ISystemUserPostService systemUserPostService;
    public UserRolePostHandle(ISystemUserRoleService systemUserRoleService, ISystemUserPostService systemUserPostService) {
        this.systemUserRoleService = systemUserRoleService;
        this.systemUserPostService = systemUserPostService;
    }

    public ISystemUserRoleService getSystemUserRoleService() {
        return systemUserRoleService;
    }

    public ISystemUserPostService getSystemUserPostService() {
        return systemUserPostService;
    }

    /**
     * 更新用户角色
     *
     * @param userId 用户id
     * @param roleId 角色id
     * @return 是否成功
     */
    public boolean updateUserRole(@NonNull Long userId, List<Long> roleId) {
        QueryWrapper<SystemUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserRoleEntity.USER_ID, userId);
        systemUserRoleService.remove(queryWrapper);
        if (!CollectionUtils.isEmpty(roleId)) {
            List<SystemUserRoleEntity> list = Lists.newArrayList();
            roleId.parallelStream().forEach((role) -> {
                SystemUserRoleEntity entity = new SystemUserRoleEntity();
                entity.setRoleId(role);
                entity.setUserId(userId);
                entity.setIsEnabled(SystemConstants.USE);
                list.add(entity);
            });
            systemUserRoleService.saveBatch(list);
        }
        return true;
    }

    /**
     * 更新用户岗位
     *
     * @param userId 用户id
     * @param postId 用户岗位
     * @return 是否成功
     */
    public boolean updateUserPost(@NonNull Long userId, List<Long> postId) {
        QueryWrapper<SystemUserPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserRoleEntity.USER_ID, userId);
        systemUserPostService.remove(queryWrapper);
        if (!CollectionUtils.isEmpty(postId)) {
            List<SystemUserPostEntity> list = Lists.newArrayList();
            postId.parallelStream().forEach((post) -> {
                SystemUserPostEntity entity = new SystemUserPostEntity();
                entity.setPostId(post);
                entity.setUserId(userId);
                entity.setIsEnabled(SystemConstants.USE);
                list.add(entity);
            });
            systemUserPostService.saveBatch(list);
        }
        return true;
    }

    /**
     * 更新用户角色和用户岗位
     *
     * @param userId  用户id
     * @param roleIds 角色id
     * @param postIds 岗位id
     * @return 是否成功
     */
    public boolean updateUserRoleAndUserPost(@NonNull Long userId, List<Long> roleIds, List<Long> postIds) {
        updateUserRole(userId, roleIds);
        updateUserPost(userId, postIds);
        return true;
    }

    /**
     * <p>
     * 根据用户id获取角色id
     * </P>
     *
     * @param userId 用户id
     * @return 角色id
     */
    public List<Long> getRoleIdByUserId(@NotNull Long userId) {
        QueryWrapper<SystemUserRoleEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserRoleEntity.USER_ID, userId);
        List<SystemUserRoleEntity> list = systemUserRoleService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.parallelStream().map(SystemUserRoleEntity::getRoleId).collect(Collectors.toList());
    }

    /**
     * <p>
     * 根据用户id获取岗位id
     * </p>
     *
     * @param userId 用户id
     * @return 岗位id
     */
    public List<Long> getPostIdByUserId(@NotNull Long userId) {
        QueryWrapper<SystemUserPostEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq(SystemUserPostEntity.USER_ID, userId);
        List<SystemUserPostEntity> list = systemUserPostService.list(queryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.parallelStream().map(SystemUserPostEntity::getPostId).collect(Collectors.toList());
    }

    /**
     * <p>
     * 根据用户获取岗位与角色id
     * </p>
     *
     * @param vo 用户信息
     */
    public void getRoleIdAndPostIdByUser(@NotNull UserVO vo) {
        Long id = vo.getId();
        if (Objects.isNull(id)) {
            return;
        }
        List<Long> roleIds = getRoleIdByUserId(id);
        vo.setRoleId(roleIds);
        List<Long> postIds = getPostIdByUserId(id);
        vo.setPostId(postIds);
    }
}
