package com.hb0730.conf.cache;

import com.hb0730.base.cache.BootAdminCache;
import com.hb0730.base.cache.ICache;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 缓存自动配置
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/2/1
 */
@Configuration
@EnableConfigurationProperties(CacheProperties.class)
@ConditionalOnProperty(prefix = "boot.admin.cache", name = "enabled", havingValue = "true", matchIfMissing = true)
public class CacheAutoConfiguration {


    @Bean
    @ConditionalOnMissingBean
    public BootAdminCache cache(
            ICache redisCache,
            CacheProperties cacheProperties) {
        BootAdminCache bootAdminCache = new BootAdminCache();
        bootAdminCache.setCache(redisCache);
        bootAdminCache.setPrefix(cacheProperties.getPrefix());
        return bootAdminCache;
    }
}
