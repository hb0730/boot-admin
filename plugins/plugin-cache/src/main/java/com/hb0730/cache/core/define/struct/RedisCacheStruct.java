package com.hb0730.cache.core.define.struct;

/**
 * redis 缓存结构
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public enum RedisCacheStruct implements CacheStruct {
    /**
     * string
     */
    STRING,

    /**
     * list
     */
    LIST,

    /**
     * hash
     */
    HASH,

    /**
     * set
     */
    SET,

    /**
     * z_set
     */
    Z_SET,

    /**
     * bit
     */
    BIT,

    /**
     * geo
     */
    GEO,

    /**
     * hyper_log_log
     */
    HYPER_LOG_LOG,

    ;

    @Override
    public String getStruct() {
        return this.name();
    }
}
