package com.hb0730.security;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/6/12
 */
public interface IUserProxyService {
    /**
     * 根据用户名获取用户信息
     *
     * @param username 用户名
     * @return 用户信息
     */
    UserInfo findUsername(String username);
}
