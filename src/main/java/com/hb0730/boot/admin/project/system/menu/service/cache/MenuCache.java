package com.hb0730.boot.admin.project.system.menu.service.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hb0730.boot.admin.cache.BootAdminCache;
import com.hb0730.boot.admin.cache.CacheUtil;
import com.hb0730.boot.admin.project.system.menu.model.dto.TreeMenuDTO;
import com.hb0730.jsons.SimpleJsonProxy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2022/7/2
 * @since 1.0.0
 */
@Component
@RequiredArgsConstructor
public class MenuCache implements CacheUtil {
    private final BootAdminCache cache;
    private final ObjectMapper objectMapper;
    private static final MenuCacheKeyValue keyValue = new MenuCacheKeyValue();

    /**
     * 设置缓存信息
     *
     * @param userId 用户id
     * @param menu   缓存菜单信息
     */
    public void setMenuCache(String userId, List<TreeMenuDTO> menu) {
        String cacheKey = getCacheKey(keyValue, userId);
        String json = SimpleJsonProxy.json.toJson(menu, objectMapper);
        cache.set(cacheKey, json);
    }


    public List<TreeMenuDTO> getMenuCache(String userId) {
        String cacheKey = getCacheKey(keyValue, userId);
        String json = cache.get(cacheKey);
        return SimpleJsonProxy.json.fromJson(json, new TypeReference<List<TreeMenuDTO>>() {
        }, objectMapper);

    }
}
