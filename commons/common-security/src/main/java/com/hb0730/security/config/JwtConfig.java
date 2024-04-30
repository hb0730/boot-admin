package com.hb0730.security.config;

import com.hb0730.security.utils.JwtUtil;
import org.springframework.context.annotation.Configuration;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
@Configuration
public class JwtConfig {
    public JwtConfig(SecurityProperties securityProperties) {
        JwtUtil.setSecurityProperties(securityProperties);
    }
}