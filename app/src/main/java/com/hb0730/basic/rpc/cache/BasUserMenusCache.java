package com.hb0730.basic.rpc.cache;

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
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/30
 */
@Component
@Slf4j
public class BasUserMenusCache implements CacheUtil {
    @Resource
    @Lazy
    private BootAdminCache cache;

    @Getter
    enum KeyValue implements CacheUtil.KeyValue {
        /**
         * 用户菜单权限
         */
        USER_ROUTE("uer_route:", EXPIRE_TIME, "用户菜单权限,user_route+商户识别码+用户ID"),
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
     * @param sysCode        商户识别码
     * @param permissionDtos 菜单与权限
     */
    public void putUserRoutes(String id, String sysCode, List<PermissionDto> permissionDtos) {
        String json = JsonUtil.DEFAULT.toJson(permissionDtos);
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, sysCode, id);
        cache.setString(cacheKey, json, KeyValue.USER_ROUTE.expire);
    }

    /**
     * 获取用户的菜单与权限
     *
     * @param id      用户id
     * @param sysCode 商户识别码
     * @return 菜单与权限
     */
    public List<PermissionDto> getUserRoutes(String id, String sysCode) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, sysCode, id);
        String json = cache.getString(cacheKey);
        return JsonUtil.DEFAULT.json2Obj(json, new TypeReference<List<PermissionDto>>() {
        });
    }

    /**
     * 删除用户的菜单与权限
     *
     * @param id      用户id
     * @param sysCode 商户识别码
     */
    public void removeUserRoutes(String id, String sysCode) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, sysCode, id);
        cache.delete(cacheKey);
    }


}
