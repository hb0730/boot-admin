package com.hb0730.basic.service;

import com.hb0730.basic.domain.BasUser;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
public interface IBasUserService {

    /**
     * 根据用户名查询系统编码
     *
     * @param username 用户名
     * @return 系统编码
     */
    String getSysCodeByUsername(String username);

    /**
     * 根据用户名查询
     *
     * @param username 用户名
     * @return 用户信息
     */
    BasUser findByUsername(String username);

    /**
     * 更新最后登录时间
     *
     * @param username 用户名
     */
    void updateLastLoginTime(String username);
}
