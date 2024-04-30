package com.hb0730.base.core;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/1
 */
public class TenantContext {
    public static final String INVOKE_CTX_USER_ID = "_rpc.user.id";
    public static final String INVOKE_CTX_USERNAME = "_rpc.user.username";
    public static final String INVOKE_CTX_SYS_CODE = "_rpc.user.sysCode";
    private static final ThreadLocal<UserInfo> THREAD_LOCAL = new ThreadLocal<>();

    public static void set(UserInfo userInfo) {
        THREAD_LOCAL.set(userInfo);
    }

    public static UserInfo get() {
        return THREAD_LOCAL.get();
    }

    public static void remove() {
        THREAD_LOCAL.remove();
    }

}