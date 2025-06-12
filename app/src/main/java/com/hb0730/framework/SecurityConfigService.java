package com.hb0730.framework;

import com.hb0730.security.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2025/2/12
 */
@Component
public class SecurityConfigService implements com.hb0730.spring.security.core.service.SecurityConfigService {
    @Override
    public Optional<String> obtainAuthorization(HttpServletRequest request) {
        return SecurityUtils.obtainAuthorization(request);
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return SecurityUtils.defaultPasswordEncoder();
    }
}
