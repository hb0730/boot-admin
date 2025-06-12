package com.hb0730.base.meta;

import com.alibaba.ttl.TransmittableThreadLocal;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/21
 */
public class UserContext {
    /**
     * 当前用户账号
     */
    private static final ThreadLocal<String> currentUserName = new TransmittableThreadLocal<>();

    public static void setCurrentUserName(String userName) {
        currentUserName.set(userName);
    }

    public static String getCurrentUserName() {
        return currentUserName.get();
    }

    public static void clear() {
        currentUserName.remove();
    }


}
