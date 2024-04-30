package com.hb0730.security.cache;

import com.hb0730.security.domain.dto.UserInfoDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public interface UserProvider {
    /**
     * 设置用户信息
     *
     * @param key key
     * @param dto 用户信息
     * @return 是否成功
     */
    boolean putUser(String key, UserInfoDto dto);

    /**
     * 获取用户信息
     *
     * @param key key
     * @return 用户信息
     */
    UserInfoDto getUser(String key);

    /**
     * 删除用户信息
     *
     * @param key key
     * @return 是否成功
     */
    boolean removeUser(String key);
}