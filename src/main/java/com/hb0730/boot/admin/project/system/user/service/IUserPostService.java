package com.hb0730.boot.admin.project.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.system.user.model.entity.UserPostEntity;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * 用户岗位  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserPostService extends IService<UserPostEntity> {

    /**
     * 根据用户id更新岗位
     *
     * @param userId  用户id
     * @param postIds 岗位id
     * @return 是否成功
     */
    boolean updateUserPost(@Nonnull Long userId, Collection<Long> postIds);

    /**
     * 根据用户id删除
     *
     * @param userId 用户id
     * @return 是否成功
     */
    boolean removeByUserId(@Nonnull Serializable userId);

    /**
     * 根据用户id删除
     *
     * @param userIds 用户id
     * @return 是否成功
     */
    boolean removeByUserIds(@Nonnull Collection<? extends Serializable> userIds);

    /**
     * 根据用户id查询岗位id
     *
     * @param userIds 用户id
     * @return 岗位id
     */
    List<Long> findPostIdByUserIds(@Nonnull Collection<Long> userIds);
}
