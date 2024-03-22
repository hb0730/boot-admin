package com.hb0730.sys.rpc.cache;

import com.fasterxml.jackson.core.type.TypeReference;
import com.hb0730.base.cache.BootAdminCache;
import com.hb0730.base.cache.CacheUtil;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.biz.dto.sys.system.PermissionDto;
import com.hb0730.biz.dto.sys.system.RoleDto;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/15
 */
@Component
@Slf4j
public class UserRoleMenusCache implements CacheUtil {
    @Lazy
    @Resource
    private BootAdminCache cache;

    @Getter
    enum KeyValue implements CacheUtil.KeyValue {
        /**
         * 用户菜单权限
         */
        USER_ROUTE("uer_route:", EXPIRE_TIME, "用户菜单权限"),
        USER_ROLE("uer_role:", EXPIRE_TIME, "用户角色"),
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
    public void putUserRoutes(Integer id, List<PermissionDto> permissionDtos) {
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
    public List<PermissionDto> getUserRoutes(Integer id) {
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
    public void clearUserRoutes(Integer id) {
        String cacheKey = getCacheKey(KeyValue.USER_ROUTE, id);
        cache.delete(cacheKey);
    }

    /**
     * 保存用户的角色
     *
     * @param id      .
     * @param roleIds .
     */
    public void putUserRoles(Integer id, List<RoleDto> roleIds) {
        String cacheKey = getCacheKey(KeyValue.USER_ROLE, id);
        cache.setString(
                cacheKey,
                JsonUtil.DEFAULT.toJson(roleIds),
                KeyValue.USER_ROLE.getExpire()
        );
    }

    /**
     * 获取用户的角色
     *
     * @param id 用户id
     * @return 角色
     */
    public List<RoleDto> getUserRoles(Integer id) {
        String cacheKey = getCacheKey(KeyValue.USER_ROLE, id);
        String json = cache.getString(cacheKey);
        if (json != null) {
            return JsonUtil.DEFAULT.json2Obj(json, new TypeReference<List<RoleDto>>() {
            });
        }
        return null;
    }

    /**
     * 清除用户的角色
     *
     * @param id 用户id
     */
    public void clearUserRoles(Integer id) {
        String cacheKey = getCacheKey(KeyValue.USER_ROLE, id);
        cache.delete(cacheKey);
    }


}
