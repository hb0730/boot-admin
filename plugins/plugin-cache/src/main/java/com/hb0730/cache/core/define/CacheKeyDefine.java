package com.hb0730.cache.core.define;

import com.hb0730.base.utils.StrUtil;
import com.hb0730.cache.core.define.struct.CacheStruct;
import com.hb0730.cache.core.define.struct.RedisCacheStruct;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 缓存key定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
@Getter
@Setter
public class CacheKeyDefine {
    /**
     * 默认缓存结构
     */
    protected static final CacheStruct DEFAULT_STRUCT = RedisCacheStruct.STRING;

    /**
     * 默认超时时间,2小时
     */
    protected static final long DEFAULT_TIMEOUT = 2 * 60 * 60;

    /**
     * 默认超时时间单位,单位秒
     */
    protected static final TimeUnit DEFAULT_UNIT = TimeUnit.SECONDS;
    /**
     * 缓存 key
     */
    private final String key;

    /**
     * 缓存描述
     */
    private final String desc;

    /**
     * 数据类型
     */
    private final Class<?> type;
    /**
     * 缓存结构
     */
    private final CacheStruct struct;
    /**
     * 超时时间
     */
    private long timeout;

    /**
     * 超时时间单位
     */
    private TimeUnit unit;

    public CacheKeyDefine(String key) {
        this(key, "", null, null, DEFAULT_TIMEOUT, DEFAULT_UNIT);
    }

    public CacheKeyDefine(String key, String desc, Class<?> type, CacheStruct struct, long timeout, TimeUnit unit) {
        this.key = key;
        this.desc = desc;
        this.type = type;
        this.struct = struct;
        this.timeout = timeout;
        this.unit = unit;
    }

    /**
     * 格式化 key 占位符 {}
     *
     * @param param param
     * @return key
     */
    public String format(Object... param) {
        return StrUtil.format(key, param);
    }

    /**
     * 格式化 key 占位符 {xxx}
     *
     * @param map map
     * @return key
     */
    public String format(Map<?, ?> map) {
        return StrUtil.format(key, map);
    }
}
