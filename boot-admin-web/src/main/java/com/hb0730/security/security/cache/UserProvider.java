package com.hb0730.security.security.cache;

import com.hb0730.security.model.dto.LoginInfoDto;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/21
 */
public interface UserProvider {
    /**
     * 设置用户信息
     *
     * @param key key
     * @param dto 用户信息
     * @return 是否成功
     */
    boolean putUser(String key, LoginInfoDto dto);

    /**
     * 获取用户信息
     *
     * @param key key
     * @return 用户信息
     */
    LoginInfoDto getUser(String key);

    /**
     * 删除用户信息
     *
     * @param key key
     * @return 是否成功
     */
    boolean removeUser(String key);

}
