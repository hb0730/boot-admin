package com.hb0730.boot.admin.commons.constant;

/**
 * <p>
 * security 常量
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SecurityConstants {
    /**
     * 登录用户 redis key
     */
    public static final String LOGIN_TOKEN_KEY = "login_tokens:";

    /**
     * 令牌前缀
     */
    public static final String TOKEN_PREFIX = "Bearer ";

    /**
     * 令牌前缀
     */
    public static final String LOGIN_USER_KEY = "login_user_key:";
}
