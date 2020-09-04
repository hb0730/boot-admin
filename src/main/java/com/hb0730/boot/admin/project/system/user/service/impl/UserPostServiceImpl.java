package com.hb0730.boot.admin.project.system.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Sets;
import com.hb0730.boot.admin.domain.service.impl.BaseServiceImpl;
import com.hb0730.boot.admin.project.system.user.mapper.IUserPostMapper;
import com.hb0730.boot.admin.project.system.user.model.entity.UserPostEntity;
import com.hb0730.boot.admin.project.system.user.service.IUserPostService;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户岗位  服务实现类
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Service
public class UserPostServiceImpl extends BaseServiceImpl<IUserPostMapper, UserPostEntity> implements IUserPostService {

    @Override
    public boolean updateUserPost(@Nonnull Long userId, Collection<Long> postIds) {
        Assert.notNull(userId, "用户id为空");
        LambdaQueryWrapper<UserPostEntity> queryWrapper = Wrappers.lambdaQuery(UserPostEntity.class)
                .eq(UserPostEntity::getUserId, userId);
        // 用户未绑定角色，应该删除用户已绑定的全部角色
        if (CollectionUtils.isEmpty(postIds)) {
            super.remove(queryWrapper);
            return true;
        }
        // 用户需要更新角色
        // 获取旧数据
        List<UserPostEntity> entities = super.list(queryWrapper);
        // 旧数据为空说明是新增用户新增角色
        if (CollectionUtils.isEmpty(entities)) {
            saveBatch(userId, postIds);
            return true;
        }
        // 新旧更替
        Set<Long> oldPostIds = entities.parallelStream().map(UserPostEntity::getPostId).collect(Collectors.toSet());

        Set<Long> newPostIds = Sets.newHashSet(postIds);

        //新数据-旧数据=新增数据  新增
        newPostIds.removeAll(oldPostIds);
        if (!CollectionUtils.isEmpty(newPostIds)) {
            saveBatch(userId, newPostIds);
        }
        // 旧数据-新数据=过期数据 删除

        oldPostIds.removeAll(postIds);
        if (!CollectionUtils.isEmpty(oldPostIds)) {
            queryWrapper = Wrappers.lambdaQuery(UserPostEntity.class)
                    .eq(UserPostEntity::getUserId, userId).in(UserPostEntity::getPostId, oldPostIds);
            remove(queryWrapper);
        }
        return true;

    }

    @Override
    public boolean removeByUserId(@Nonnull Serializable userId) {
        Assert.notNull(userId, "用户id不为空");
        LambdaQueryWrapper<UserPostEntity> queryWrapper = Wrappers
                .lambdaQuery(UserPostEntity.class)
                .eq(UserPostEntity::getUserId, userId);
        return super.remove(queryWrapper);
    }

    @Override
    public boolean removeByUserIds(@Nonnull Collection<? extends Serializable> userIds) {
        Assert.notEmpty(userIds, "用户id不为空");
        LambdaQueryWrapper<UserPostEntity> queryWrapper = Wrappers
                .lambdaQuery(UserPostEntity.class)
                .in(UserPostEntity::getUserId, userIds);
        return super.remove(queryWrapper);
    }

    private void saveBatch(@Nonnull Long userId, @Nonnull Collection<Long> postIds) {
        Assert.notNull(userId, "用户id为空");
        Assert.notEmpty(postIds, "角色id为空");
        List<UserPostEntity> entities = new ArrayList<>(postIds.size());
        for (Long postId : postIds) {
            UserPostEntity entity = new UserPostEntity();
            entity.setUserId(userId);
            entity.setPostId(postId);
            entities.add(entity);
        }
        saveBatch(entities);
    }
}
