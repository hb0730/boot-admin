package com.hb0730.sys.system.rpc.cache;

import com.hb0730.base.utils.JsonUtil;
import com.hb0730.cache.core.BootAdminCache;
import com.hb0730.cache.core.CacheUtil;
import com.hb0730.sys.system.domain.SysOssConfig;
import jakarta.annotation.Resource;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/20
 */
@Component
@Slf4j
public class OssCache implements CacheUtil {
    @Resource
    private BootAdminCache cache;

    @Getter
    enum KeyValue implements CacheUtil.KeyValue {
        /**
         * oss配置
         */
        OSS_CONFIG("oss_config:", EXPIRE_TIME, "oss配置"),
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
     * 缓存oss配置
     *
     * @param key    key
     * @param config oss配置
     */
    public void put(String key, SysOssConfig config) {
        String cacheKey = this.getCacheKey(KeyValue.OSS_CONFIG, key);
        String json = JsonUtil.DEFAULT.toJson(config);
        cache.setString(cacheKey, json, KeyValue.OSS_CONFIG.getExpire());
    }

    /**
     * 获取oss配置
     *
     * @param key key
     * @return oss配置
     */
    public Optional<SysOssConfig> get(String key) {
        String cacheKey = this.getCacheKey(KeyValue.OSS_CONFIG, key);
        String json = cache.getString(cacheKey);
        return Optional.ofNullable(json).map(s -> JsonUtil.DEFAULT.json2Obj(s, SysOssConfig.class));
    }

    /**
     * 删除oss配置
     *
     * @param key key
     */
    public void delete(String key) {
        String cacheKey = this.getCacheKey(KeyValue.OSS_CONFIG, key);
        cache.delete(cacheKey);
    }


}
