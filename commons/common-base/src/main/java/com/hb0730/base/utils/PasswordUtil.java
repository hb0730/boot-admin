package com.hb0730.base.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/23
 */
public class PasswordUtil {
    /**
     * 将原始密码进行编码,使用默认编码器{@link #defaultPasswordEncoder()}
     *
     * @param rawPassword 原密码
     * @return 编码后的密码
     */
    public static String encoder(String rawPassword) {
        return encoder(rawPassword, defaultPasswordEncoder());
    }

    /**
     * 将原始密码进行编码
     *
     * @param rawPassword     原密码
     * @param passwordEncoder 编码器
     * @return 编码后的密码
     */
    public static String encoder(String rawPassword, PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(rawPassword);
    }

    /**
     * 校验原始密码与编码后的密码是否为同一个密码，使用默认编码器{@link #defaultPasswordEncoder()}
     *
     * @param rawPassword     原密码
     * @param encodedPassword 编码后的密码
     * @return 是否一致
     */
    public static boolean matches(String rawPassword, String encodedPassword) {
        return matches(rawPassword, encodedPassword, defaultPasswordEncoder());
    }

    /**
     * 校验原始密码与编码后的密码是否为同一个密码
     *
     * @param rawPassword     原密码
     * @param encodedPassword 编码后的密码
     * @param passwordEncoder 编码器
     * @return 是否一致
     */
    public static boolean matches(CharSequence rawPassword, String encodedPassword, PasswordEncoder passwordEncoder) {
        return defaultPasswordEncoder().matches(rawPassword, encodedPassword);
    }

    /**
     * 默认密码加密,使用BCryptPasswordEncoder
     *
     * @return {@link PasswordEncoder}
     */
    public static PasswordEncoder defaultPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}