package com.hb0730.spring.security.core.service;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

/**
 * 获取授权 TOKEN
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/2/11
 */
public interface SecurityConfigService {
    /**
     * 获取授权 TOKEN
     *
     * @return token
     */
    Optional<String> obtainAuthorization(HttpServletRequest request);

    /**
     * 获取 passwordEncoder
     */
    default PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(4);
    }
}
