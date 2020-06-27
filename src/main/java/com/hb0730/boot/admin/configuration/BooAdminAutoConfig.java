package com.hb0730.boot.admin.configuration;

import com.hb0730.boot.admin.commons.constant.enums.TokenTypeEnum;
import com.hb0730.boot.admin.configuration.properties.BootAdminProperties;
import com.hb0730.boot.admin.security.service.ITokenService;
import com.hb0730.boot.admin.security.service.impl.InMemoryTokenServiceImpl;
import com.hb0730.boot.admin.security.service.impl.RedisTokenServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
@AllArgsConstructor
@Configuration
public class BooAdminAutoConfig {
    @Autowired
    private final BootAdminProperties properties;

    @Bean
    public ITokenService tokenService() {
        TokenTypeEnum tokenType = properties.getTokenType();
        ITokenService service = null;
        switch (tokenType) {
            case REDIS:
                service = new RedisTokenServiceImpl(properties);
                break;
            case LOCAL:
                service = new InMemoryTokenServiceImpl(properties);
                break;
            default:
                service = new InMemoryTokenServiceImpl(properties);
        }
        return service;
    }
}
