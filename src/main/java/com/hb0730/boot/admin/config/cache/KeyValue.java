package com.hb0730.boot.admin.config.cache;

/**
 * 缓存定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
public interface KeyValue {

    /**
     * 缓存key的失效时间
     * <p> 不设置失效时间 == 永久有效 ；
     * XXX: setObject方法设为-1时，经封装后代表永久有效
     * <p> 单位：秒
     */
    long EXPIRE_TIME_DEFAULT = -1;

    /**
     * 工单号在缓存存储时间 90天
     */
    long ORDER_TIME_OUT_LIMIT = 60 * 60 * 24 * 30;
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
