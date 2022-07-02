package com.hb0730.boot.admin.project.system.dict.service.cache;

import com.hb0730.boot.admin.cache.KeyValue;

import java.util.List;

/**
 * 字典缓存-参数信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
public class DictKeyValue implements KeyValue {
    @Override
    public String getPrefix() {
        return "dict";
    }

    @Override
    public long getExpire() {
        return 0L;
    }

    @Override
    public Class<?> getClazz() {
        return List.class;
    }

    @Override
    public String getName() {
        return "字典缓存";
    }

    @Override
    public String getDesc() {
        return "缓存当前系统启动下的所有字段信息";
    }
}
