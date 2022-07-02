package com.hb0730.boot.admin.project.system.menu.service.cache;

import com.hb0730.boot.admin.cache.KeyValue;

import java.util.List;

/**
 * 菜单缓存-参数信息
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
public class MenuCacheKeyValue implements KeyValue {
    @Override
    public String getPrefix() {
        return "menu";
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
        return "菜单缓存";
    }

    @Override
    public String getDesc() {
        return "根据用户id缓存菜单信息";
    }
}
