package com.hb0730.base.tenant;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/4/1
 */
public class TenantContext {
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

    /**
     * 当前用户信息
     */
    @Data
    @EqualsAndHashCode
    public static class UserInfo implements Serializable {
        private String username;
        private String sysCode;


        public UserInfo(String username, String sysCode) {
            this.username = username;
            this.sysCode = sysCode;
        }

        public UserInfo() {
        }
    }
}
