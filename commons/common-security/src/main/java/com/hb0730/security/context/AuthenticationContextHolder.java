package com.hb0730.security.context;

/**
 * 认证上下文
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public class AuthenticationContextHolder {
    private static final ThreadLocal<AuthenticationContext> CONTEXT = new ThreadLocal<>();

    public static void setContext(AuthenticationContext context) {
        CONTEXT.set(context);
    }

    public static AuthenticationContext getContext() {
        return CONTEXT.get();
    }

    public static void clear() {
        CONTEXT.remove();
    }
}