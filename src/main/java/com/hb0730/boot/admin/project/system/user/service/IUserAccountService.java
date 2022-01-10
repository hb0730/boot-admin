package com.hb0730.boot.admin.project.system.user.service;

import com.hb0730.boot.admin.domain.service.ISuperBaseService;
import com.hb0730.boot.admin.project.system.user.model.dto.UserAccountDTO;
import com.hb0730.boot.admin.project.system.user.model.entity.UserAccountEntity;
import com.hb0730.boot.admin.project.system.user.model.query.UserAccountParams;
import org.springframework.lang.NonNull;

import javax.annotation.Nonnull;
import java.io.Serializable;
import java.util.Collection;

/**
 * 用户账号  服务类
 *
 * @author bing_huang
 * @since 3.0.0
 */
public interface IUserAccountService extends ISuperBaseService<Long, UserAccountParams, UserAccountDTO, UserAccountEntity> {
    /**
     * 根据用户获取账号信息
     *
     * @param userId 用户id
     * @return 账号信息，如果用户id为<code>null</code>,返回<code>null</code>
     */
    UserAccountEntity userAccountByUserId(Long userId);

    /**
     * 根据用户账号查找用户账号信息
     *
     * @param username 用户账号
     * @return 账号信息，如果用户账号为<code>null</code>,返回<code>null</code>
     */
    UserAccountEntity findUserAccountByUsername(String username);

    /**
     * 更新面膜
     *
     * @param userId      用户id,不为空
     * @param oldPassword 原密码，不为空
     * @param newPassword 新密码，不为空
     * @return 是否成功
     */
    boolean updatePassword(@NonNull Long userId, @NonNull String oldPassword, @NonNull String newPassword);

    /**
     * 重置密码
     *
     * @param userId   用户id
     * @param password 密码
     * @return 是否成功
     */
    boolean updatePassword(@Nonnull Long userId, @Nonnull String password);

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
    boolean removeByUserIds(@Nonnull Collection<?> userIds);
}
