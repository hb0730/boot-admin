package com.hb0730.boot.admin.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * </P>
 *
 * @author bing_huang
 * @since V1.0
 */
public class SecurityUtils {
    /**
     * 获取Authentication
     */
    public static Authentication getAuthentication()
    {
        return SecurityContextHolder.getContext().getAuthentication();
    }
}
