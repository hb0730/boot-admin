package com.hb0730.boot.admin.commons.constant;

import java.io.File;

/**
 * <p>
 * 系统常量
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SystemConstants {
    /**
     * cache key
     */
    public static final String REDIS_CACHE = "system_constant";
    public static final String REDIS_DICT_LABEL = "label";
    public static final String REDIS_DICT_VALUE = "value";

    /**
     * User home directory.
     */
    public final static String USER_HOME = System.getProperties().getProperty("user.home");

    /**
     * 启用
     */
    public static final int ENABLED = 1;
    public static final String USE_CACHE = "USE";

    /**
     * 禁用
     */
    public static final int UN_ENABLED = 0;
    public static final String NOT_USE_CACHE = "NOT_USE_CACHE";

    /**
     * 是否全部
     */
    public static final int IS_ALL = -1;
    public static final String IS_ALL_CACHE = "IS_ALL";
    /**
     * 修改
     */
    public static final int IS_UPDATE = 1;
    public static final String IS_UPDATE_CACHE = "IS_UPDATE";
    /**
     * 非修改
     */
    public static final int NO_UPDATE = 0;
    public static final String NO_UPDATE_CACHE = "NO_UPDATE";
    /**
     * 默认父id为-1
     */
    public static final long PARENT_ID = -1L;
    public static final String PARENT_ID_CACHE = "PARENT_ID";

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    public static final String DEFAULT_CACHE_PASSWORD = "DEFAULT_PASSWORD";

    /**
     * 路径分隔符
     */
    public static final String FILE_SEPARATOR = File.separator;

    /**
     * redis 缓存 前缀
     */
    public static class DictConstants {
        /**
         * 字典表缓存的key
         */
        public static final String DICT_KEY_PREFIX = "system_dict:";
    }

    /**
     * security 常量
     */
    public static class SecurityConstants {
        /**
         * 登录用户 redis key
         */
        public static final String LOGIN_TOKEN_KEY_PREFIX = "login_tokens:";

        /**
         * 令牌前缀
         */
        public static final String LOGIN_USER_KEY_PREFIX = "login_user_key:";
    }
}
