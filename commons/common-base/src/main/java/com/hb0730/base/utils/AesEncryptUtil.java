package com.hb0730.base.utils;

import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/22
 */
public class AesEncryptUtil {
    public static class SecKey {

        private static final String key = "1234567890adbcde"; // 长度为16个字符

        private static final String iv = "1234567890hjlkew"; // 长度为16个字符
    }

    /**
     * 加密方法,AES/ECB/PKCS5Padding
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @param iv   加密iv
     * @return 加密的结果, 16进制
     */
    public static String encrypt(String data, String key, String iv) {
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return aes.encryptHex(data.getBytes());
    }

    /**
     * 解密方法,AES/ECB/PKCS5Padding
     *
     * @param data 要解密的数据 16进制Or Base64
     * @param key  解密key
     * @param iv   解密iv
     * @return 解密的结果
     */
    public static String decrypt(String data, String key, String iv) {
        AES aes = new AES(Mode.ECB, Padding.PKCS5Padding, key.getBytes(), iv.getBytes());
        return aes.decryptStr(data);
    }

    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @return 加密的结果
     */
    public static String encrypt(String data) {
        return encrypt(data, SecKey.key, SecKey.iv);
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @return 解密的结果
     */
    public static String decrypt(String data) {
        return decrypt(data, SecKey.key, SecKey.iv);
    }

    /**
     * 加密方法
     *
     * @param data 要加密的数据
     * @param key  加密key
     * @return 加密的结果
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, key, SecKey.iv);
    }

    /**
     * 解密方法
     *
     * @param data 要解密的数据
     * @param key  解密key
     * @return 解密的结果
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, key, SecKey.iv);
    }
}