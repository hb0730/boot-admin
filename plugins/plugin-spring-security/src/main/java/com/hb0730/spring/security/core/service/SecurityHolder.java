package com.hb0730.spring.security.core.service;

import com.hb0730.base.meta.IUserInfo;

import java.util.Optional;

/**
 * SecurityUtils 的 bean 对象
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public interface SecurityHolder {
    /**
     * 获取当前用户
     *
     * @return 当前用户
     */
    Optional<IUserInfo> getLoginUser();

    /**
     * 获取当前用户id
     *
     * @return id
     */
    Optional<String> getLoginUserId();
}
