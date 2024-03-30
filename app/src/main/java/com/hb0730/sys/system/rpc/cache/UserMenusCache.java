package com.hb0730.sys.system.rpc.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.cache.core.BootAdminCache;
import com.hb0730.cache.core.CacheUtil;
import com.hb0730.rpc.sys.system.domain.PermissionDto;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 用户菜单缓存
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/26
 */
@Component
@Slf4j
public class UserMenusCache implements CacheUtil {
    @Lazy
    @Resource
    private BootAdminCache cache;

    @Getter
    enum KeyValue implements CacheUtil.KeyValue {
        /**
         * 用户菜单权限
         */
        USER_ROUTE("uer_route:", EXPIRE_TIME, "用户菜单权限"),
        ;
        private final String prefix;
        /**
         * 过期时间（秒）
         */
        private final int expire;
        /**
         * 描述
         */
        private final String desc;

        KeyValue(String prefix, int expire, String desc) {
            this.prefix = prefix;
            this.expire = expire;
            this.desc = desc;
        }
    }

    /**
     * 设置用户的菜单与权限
     *
     * @param id             用户id
     * @param permissionDtos 菜单与权限
     */
    public void putUserRoutes(Long id, List<PermissionDto> permissionDtos) {
        String json = JsonUtil.DEFAULT.toJson(permissionDtos);
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, id);
        cache.setString(
                cacheKey,
                json,
                KeyValue.USER_ROUTE.getExpire()
        );
    }

    /**
     * 获取用户的菜单与权限
     *
     * @param id 用户id
     * @return 菜单与权限
     */
    public List<PermissionDto> getUserRoutes(Long id) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, id);
        String json = cache.getString(cacheKey);
        if (json != null) {
            return JsonUtil.DEFAULT.json2Obj(json, new TypeReference<List<PermissionDto>>() {
            });
        }
        return null;
    }

    /**
     * 清除用户的菜单与权限
     *
     * @param id 用户id
     */
    public void clearUserRoutes(Long id) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, id);
        cache.delete(cacheKey);
    }
}
