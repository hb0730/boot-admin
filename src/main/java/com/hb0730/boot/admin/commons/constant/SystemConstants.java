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

    /**
     * 修改
     */
    public static final int IS_UPDATE = 1;
    /**
     * 非修改
     */
    public static final int NO_UPDATE = 0;
    /**
     * 默认父id为-1
     */
    public static final long PARENT_ID = -1L;

    /**
     * 默认密码
     */
    public static final String DEFAULT_PASSWORD = "123456";

    /**
     * 路径分隔符
     */
    public static final String FILE_SEPARATOR = File.separator;

    public static class RedisConstants {
        /**
         * jetcache area
         */
        public static final String REDIS_JETCACHE_AREA = "boot-admin";
        /**
         * jetcache 登录缓存
         */
        public static final String REDIS_JETCACHE_NAME_LOGIN = "login_cache";

        /**
         * 字典缓存
         */
        public static final String REDIS_JETCACHE_NAME_DICT = "dict_cache";
        /**
         * 字典表缓存的key
         */
        public static final String REDIS_JETCACHE_KEY_DICT = "system_dict:";
    }
}
