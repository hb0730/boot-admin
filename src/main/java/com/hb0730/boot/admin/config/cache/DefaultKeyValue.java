package com.hb0730.boot.admin.config.cache;

/**
 * 非系统级别的缓存定义
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/6/6
 */
public enum DefaultKeyValue implements KeyValue {

    /**
     * 【系统配置项】
     * --------------------------------------------------------------------------------
     * <p>系统配置表【sys_config】缓存</p>
     * --------------------------------------------------------------------------------
     */
    SYS_CONFIG("SYS",
            EXPIRE_TIME_DEFAULT,
            CacheType.STRING,
            String.class,
            "系统配置",
            "SYS:key --> (String)value"),

    ;
    /**
     * KEY前缀
     */
    private final String prefix;
    /**
     * 过期时间（秒）
     */
    private final long expire;
    /**
     * 缓存对象
     */
    private final Class<?> clazz;
    /**
     * 缓存名称
     */
    private final String name;

    /**
     * 缓存说明
     */
    private final String desc;
    /**
     * 缓存的数据类型 {涉及JimDB缓存的  get、set 方法使用}
     */
    private CacheType type;

    DefaultKeyValue(String prefix, long expire, CacheType type, Class<?> clazz, String name, String desc) {
        this.prefix = prefix;
        this.expire = expire;
        this.clazz = clazz;
        this.name = name;
        this.desc = desc;
        this.type = type;
    }

    @Override
    public String getPrefix() {
        return prefix;
    }

    @Override
    public long getExpire() {
        return expire;
    }

    @Override
    public Class<?> getClazz() {
        return clazz;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDesc() {
        return desc;
    }

    public CacheType getType() {
        return type;
    }

    /**
     * JimDB缓存的数据类型
     * <p> 涉及如何 set、get </p>
     */
    public enum CacheType {
        STRING, OBJECT, HASHMAP, SET, LIST
    }
}
