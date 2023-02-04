package com.hb0730.boot.admin.base.util;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import jakarta.annotation.Nonnull;
import lombok.Data;

import java.nio.charset.StandardCharsets;

/**
 * AES 加密
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/30
 */
public class AesEncryptUtil {
    @Data
    public static class SecKey {

        public static String key = "1234567890adbcde"; // 长度为16个字符

        public static String iv = "1234567890hjlkew"; // 长度为16个字符
    }

    /**
     * 加密方法，使用默认的密钥与偏移向量
     *
     * @param data 要加密的数据
     * @return 加密的结果
     */
    public static String encrypt(String data) {
        return encrypt(data, SecKey.key, SecKey.iv);
    }

    /**
     * 加密方式
     *
     * @param data 要加密的数据
     * @param key  密钥
     * @param iv   偏移向量，加盐
     * @return 加密的结果
     */
    public static String encrypt(String data, @Nonnull String key, @Nonnull String iv) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8),
                iv.getBytes(StandardCharsets.UTF_8));
        return aes.encryptHex(data);
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @return 解密的结果
     */
    public static String desEncrypt(String data) {
        return desEncrypt(data, SecKey.key, SecKey.iv);
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  密钥
     * @param iv   偏移向量，加盐
     * @return 解密的结果
     */
    public static String desEncrypt(String data, @Nonnull String key, @Nonnull String iv) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, key.getBytes(StandardCharsets.UTF_8),
                iv.getBytes(StandardCharsets.UTF_8));
        return aes.decryptStr(data);
    }

}
