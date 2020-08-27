package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.token.ITokenService;
import com.hb0730.boot.admin.token.impl.RedisTokenServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * boot admin 配置
 *
 * @author bing_huang
 * @since 3.0.0
 */
@Configuration
@RequiredArgsConstructor
public class BooAdminAutoConfiguration {
    private final BootAdminProperties properties;

    /**
     * 配置redis token缓存
     *
     * @return {@link ITokenService}
     */
    @Bean
    public ITokenService tokenService() {
        return new RedisTokenServiceImpl(properties.getTokenConfig());
    }
}
