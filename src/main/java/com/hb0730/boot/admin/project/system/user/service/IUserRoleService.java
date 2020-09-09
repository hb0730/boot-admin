package com.hb0730.boot.admin.project.system.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hb0730.boot.admin.project.system.user.model.entity.UserRoleEntity;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Collection;

/**
 * 用户角色  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserRoleService extends IService<UserRoleEntity> {

    /**
     * 更新用户角色信息<br>
     * 包含新增，删除
     *
     * @param userId  用户id
     * @param roleIds 角色id
     * @return 是否成功
     */
    boolean updateUserRole(@Nonnull Long userId, Collection<Long> roleIds);

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
     * 根据id查询角色id
     *
     * @param userId 用户id
     * @return 角色id
     */
    @Nullable
    Collection<Long> findRoleByUserId(@Nonnull Long userId);
}
