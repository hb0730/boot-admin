package com.hb0730.base.pool;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/6/11
 */
public interface ConstPool {
    /**
     * Header for access token
     */
    String HEADER_X_ACCESS_TOKEN = "X-Access-Token";

    /**
     * 登录用户权限缓存KEY前缀
     */
    String LOGIN_USER_CACHE_PREFIX = "user:info";
    /**
     * 字典项缓存KEY前缀
     */
    String DICT_ITEMS_KEY = "sys:dict:items";

}
