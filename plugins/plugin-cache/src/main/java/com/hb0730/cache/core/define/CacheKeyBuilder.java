package com.hb0730.cache.core.define;

import cn.hutool.core.builder.Builder;
import com.hb0730.cache.core.define.struct.CacheStruct;

import java.util.concurrent.TimeUnit;

/**
 * 缓存key构建器
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public class CacheKeyBuilder implements Builder<CacheKeyDefine> {
    /**
     * 缓存key
     */
    private String key;

    /**
     * 缓存描述
     */
    private String desc;

    /**
     * 数据类型
     */
    private Class<?> type;

    /**
     * 数据结构
     */
    private CacheStruct struct;

    /**
     * 超时时间
     */
    private long timeout;

    /**
     * 超时时间单位
     */
    private TimeUnit unit;

    public CacheKeyBuilder() {
        this.struct = CacheKeyDefine.DEFAULT_STRUCT;
        this.timeout = CacheKeyDefine.DEFAULT_TIMEOUT;
        this.unit = CacheKeyDefine.DEFAULT_UNIT;
    }

    /**
     * 设置缓存key
     *
     * @param key 缓存key
     * @return this
     */
    public CacheKeyBuilder key(String key) {
        this.key = key;
        return this;
    }

    /**
     * 设置缓存描述
     *
     * @param desc 缓存描述
     * @return this
     */
    public CacheKeyBuilder desc(String desc) {
        this.desc = desc;
        return this;
    }

    /**
     * 设置数据类型
     *
     * @param type 数据类型
     * @return this
     */
    public CacheKeyBuilder type(Class<?> type) {
        this.type = type;
        return this;
    }

    /**
     * 设置数据结构
     *
     * @param struct 数据结构
     * @return this
     */
    public CacheKeyBuilder struct(CacheStruct struct) {
        this.struct = struct;
        return this;
    }

    /**
     * 设置超时时间
     *
     * @param timeout 超时时间
     * @return this
     */
    public CacheKeyBuilder timeout(long timeout) {
        this.timeout = timeout;
        return this;
    }

    /**
     * 设置超时时间
     *
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return this
     */
    public CacheKeyBuilder timeout(long timeout, TimeUnit unit) {
        this.timeout = timeout;
        this.unit = unit;
        return this;
    }

    /**
     * 设置超时时间单位
     *
     * @param unit 时间单位
     * @return this
     */
    public CacheKeyBuilder unit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    /**
     * 构建缓存key
     *
     * @return 缓存key
     */
    public static CacheKeyBuilder create() {
        return new CacheKeyBuilder();
    }

    @Override
    public CacheKeyDefine build() {
        return new CacheKeyDefine(key, desc, type, struct, timeout, unit);
    }
}
