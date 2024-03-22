package com.hb0730.security.security.cache.imple;

import cn.hutool.core.util.RandomUtil;
import com.hb0730.base.cache.BootAdminCache;
import com.hb0730.base.utils.JsonUtil;
import com.hb0730.base.utils.StrUtil;
import com.hb0730.security.config.SecurityProperties;
import com.hb0730.security.model.dto.LoginInfoDto;
import com.hb0730.security.security.cache.UserProvider;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/22
 */
@Component
@Slf4j
public class UserRedisCache implements UserProvider {
    @Lazy
    @Resource
    private SecurityProperties properties;
    @Lazy
    @Resource
    private BootAdminCache cache;

    @Override
    public boolean putUser(String key, LoginInfoDto dto) {
        if (StrUtil.isBlank(key) || dto == null) {
            return false;
        }
        String cacheKey = getCacheKey(key);
        String userJson = JsonUtil.DEFAULT.toJson(dto);
        // 避免token过期时间和用户信息过期时间一致
        int expireTime = properties.getTokenValidity() + RandomUtil.randomInt(900, 1800);
        return cache.setString(cacheKey, userJson, expireTime);
    }

    @Override
    public LoginInfoDto getUser(String key) {
        if (StrUtil.isBlank(key)) {
            return null;
        }
        String cacheKey = getCacheKey(key);
        String userJson = cache.getString(cacheKey);
        if (StrUtil.isNotBlank(userJson)) {
            return JsonUtil.DEFAULT.json2Obj(userJson, LoginInfoDto.class);
        }
        return null;
    }

    @Override
    public boolean removeUser(String key) {
        if (StrUtil.isBlank(key)) {
            return false;
        }
        String cacheKey = getCacheKey(key);
        cache.delete(cacheKey);
        return true;
    }

    private String getCacheKey(String key) {
        return "login_info" + ":" + key;
    }
}
