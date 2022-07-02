package com.hb0730.boot.admin.project.system.option.service.cache;

import cn.hutool.core.util.StrUtil;
import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.boot.admin.cache.BootAdminCache;
import com.hb0730.boot.admin.cache.CacheUtil;
import com.hb0730.jsons.SimpleJsonProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class OptionsCache implements CacheUtil {
    private final static OptionsKeyValue keyValue = new OptionsKeyValue();
    private final BootAdminCache cache;

    public void setOptions(Map<String, Object> values) {
        String cacheKey = getCacheKey(keyValue);
        cache.set(cacheKey, SimpleJsonProxy.json.toJson(values));
    }

    public Map<String, Object> getOptions() {
        String cacheKey = getCacheKey(keyValue);
        String json = cache.get(cacheKey);
        if (StrUtil.isNotBlank(json)) {
            return SimpleJsonProxy.json.fromJson(json, new TypeReference<Map<String, Object>>() {
            });
        }
        return null;
    }

    public void delOptions(){
        String cacheKey = getCacheKey(keyValue);
        cache.del(cacheKey);
    }
}
