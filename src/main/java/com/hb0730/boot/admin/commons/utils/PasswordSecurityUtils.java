package com.hb0730.boot.admin.commons.utils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密码加密
 *
 * @author bing_huang
 */
public class PasswordSecurityUtils {

    /**
     * 密码加密
     *
     * @param encoder  加密类型
     * @param password 原密码
     * @return 加密后密码
     */
    public static String encode(PasswordEncoder encoder, final String password) {
        return encoder.encode(password);
    }

    /**
     * 校验密码是否正确
     *
     * @param encoder         加密类型
     * @param rawPassword     原密码
     * @param encodedPassword 加密后的密码
     * @return 是否正确
     */
    public static boolean matches(PasswordEncoder encoder, final String rawPassword, final String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

}
