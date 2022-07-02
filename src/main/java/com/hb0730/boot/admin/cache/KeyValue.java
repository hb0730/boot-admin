package com.hb0730.boot.admin.cache;

/**
 * 缓存定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
public interface KeyValue {
    /**
     * KEY前缀
     *
     * @return 前缀
     */
    default String getPrefix() {
        return "";
    }

    /**
     * 过期时间（秒）
     *
     * @return 过期时间
     */
    default long getExpire() {
        return 0L;
    }

    /**
     * 缓存对象
     *
     * @return 缓存对象
     */
    default Class<?> getClazz() {
        return null;
    }

    /**
     * 缓存名称
     *
     * @return 缓存名称
     */
    default String getName() {
        return "";
    }

    /**
     * 缓存说明
     */
    default String getDesc() {
        return "";
    }

}
