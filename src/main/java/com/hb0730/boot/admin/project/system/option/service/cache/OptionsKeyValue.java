package com.hb0730.boot.admin.project.system.option.service.cache;

import com.hb0730.boot.admin.cache.KeyValue;

import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
public class OptionsKeyValue implements KeyValue {
    @Override
    public String getPrefix() {
        return "options";
    }

    @Override
    public long getExpire() {
        return 0L;
    }

    @Override
    public Class<?> getClazz() {
        return Map.class;
    }

    @Override
    public String getName() {
        return "单一选项";
    }

    @Override
    public String getDesc() {
        return "选择项";
    }
}
