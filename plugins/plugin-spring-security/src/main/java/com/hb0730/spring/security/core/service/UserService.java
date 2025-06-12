package com.hb0730.spring.security.core.service;


import com.hb0730.spring.security.core.domain.IUserInfo;

/**
 * 获取用户信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/24
 */
public interface UserService {

    /**
     * 通过 token 获取用户信息
     *
     * @param token token
     * @return user
     */
    IUserInfo getUserByToken(String token);
}
