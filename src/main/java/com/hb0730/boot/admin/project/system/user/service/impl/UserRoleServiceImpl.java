package com.hb0730.boot.admin.project.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.domain.service.impl.BaseServiceImpl;
import com.hb0730.boot.admin.project.system.user.mapper.IUserRoleMapper;
import com.hb0730.boot.admin.project.system.user.model.entity.UserRoleEntity;
import com.hb0730.boot.admin.project.system.user.service.IUserRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户角色  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<IUserRoleMapper, UserRoleEntity> implements IUserRoleService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateUserRole(@Nonnull Long userId, Collection<Long> roleIds) {
        Assert.notNull(userId, "用户id为空");
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = Wrappers.lambdaQuery(UserRoleEntity.class)
                .eq(UserRoleEntity::getUserId, userId);
        // 用户未绑定角色，应该删除用户已绑定的全部角色
        if (CollectionUtils.isEmpty(roleIds)) {
            super.remove(queryWrapper);
            return true;
        }
        // 用户需要更新角色
        // 获取旧数据
        List<UserRoleEntity> entities = super.list(queryWrapper);
        // 旧数据为空说明是新增用户新增角色
        if (CollectionUtils.isEmpty(entities)) {
            saveBatch(userId, roleIds);
            return true;
        }
        // 新旧更替
        Set<Long> oldRoleIds = entities.parallelStream().map(UserRoleEntity::getRoleId).collect(Collectors.toSet());

        Set<Long> newRoleIds = Sets.newHashSet(roleIds);

        //新数据-旧数据=新增数据  新增
        newRoleIds.removeAll(oldRoleIds);
        if (!CollectionUtils.isEmpty(newRoleIds)) {
            saveBatch(userId, newRoleIds);
        }
        // 旧数据-新数据=过期数据 删除

        oldRoleIds.removeAll(roleIds);
        if (!CollectionUtils.isEmpty(oldRoleIds)) {
            queryWrapper = Wrappers.lambdaQuery(UserRoleEntity.class)
                    .eq(UserRoleEntity::getUserId, userId).in(UserRoleEntity::getRoleId, oldRoleIds);
            remove(queryWrapper);
        }
        return true;

    }

    @Override
    public boolean removeByUserId(@Nonnull Serializable userId) {
        Assert.notNull(userId, "用户id为空");
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = Wrappers.lambdaQuery(UserRoleEntity.class)
                .eq(UserRoleEntity::getUserId, userId);
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByUserIds(@Nonnull Collection<?> userIds) {
        Assert.notEmpty(userIds, "用户id为空");
        LambdaQueryWrapper<UserRoleEntity> queryWrapper = Wrappers.lambdaQuery(UserRoleEntity.class)
                .in(UserRoleEntity::getUserId, userIds);
        return super.remove(queryWrapper);
    }

    @Override
    @Nullable
    public Collection<Long> findRoleByUserId(@Nonnull Long userId) {
        Assert.notNull(userId, "用户id不为空");
        LambdaQueryWrapper<UserRoleEntity> query = Wrappers.lambdaQuery(UserRoleEntity.class)
                .eq(UserRoleEntity::getUserId, userId).select(UserRoleEntity::getRoleId);
        List<UserRoleEntity> list = super.list(query);
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        return list.parallelStream().map(UserRoleEntity::getRoleId).collect(Collectors.toSet());
    }

    private void saveBatch(@Nonnull Long userId, @Nonnull Collection<Long> roleIds) {
        Assert.notNull(userId, "用户id为空");
        Assert.notEmpty(roleIds, "角色id为空");
        List<UserRoleEntity> entities = new ArrayList<>(roleIds.size());
        for (Long roleId : roleIds) {
            UserRoleEntity entity = new UserRoleEntity();
            entity.setUserId(userId);
            entity.setRoleId(roleId);
            entities.add(entity);
        }
        saveBatch(entities);
    }
}
