package com.hb0730.security.context;

import lombok.Getter;
import lombok.Setter;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Getter
@Setter
public class AuthenticationContext {
    /**
     * 用户名
     */
    private String username;
    /**
     * 是否租户登录，租户登录的用户为管理员，非租户登录的用户为普通用户
     */
    private Boolean tenantLogin;
}