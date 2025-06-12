package com.hb0730.base.utils;

import cn.hutool.crypto.KeyUtil;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * AES加解密工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public class AesCryptoUtil {
    public static final String key = "1234567890adbcde"; // 长度为16个字符

    public static final String iv = "1234567890hjlkew"; // 长度为16个字符

    public static final String mode = "AES/CBC/PKCS5Padding";

    /**
     * AES加密,使用默认模式 AES/CBC/PKCS5Padding,默认密钥和偏移量
     *
     * @param data 待加密数据
     * @return 加密后的Hex
     */
    public static String encrypt(String data) {
        return encrypt(data, mode, key, iv);
    }

    /**
     * AES加密,使用AES/ECB/PKCS5Padding模式
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return 加密后的Hex
     */
    public static String encrypt(String data, String key) {
        return encrypt(data, "AES/ECB/PKCS5Padding", key, null);
    }


    /**
     * AES加密,使用默认模式 AES/CBC/PKCS5Padding
     *
     * @param data 待加密数据
     * @param key  密钥
     * @param iv   偏移量
     * @return 加密后的Hex
     */
    public static String encrypt(String data, String key, String iv) {
        return encrypt(data, mode, key, iv);
    }

    /**
     * AES加密
     *
     * @param data 待加密数据
     * @param mode 模式 AES/CBC/PKCS5Padding，AES/ECB/PKCS5Padding等
     * @param key  密钥
     * @param iv   偏移量
     * @return 加密后的Hex
     */
    public static String encrypt(String data, String mode, String key, String iv) {
        SecretKey secretKey = KeyUtil.generateKey("AES", key.getBytes());
        IvParameterSpec ivParameterSpec = null;
        if (StrUtil.isNotBlank(iv)) {
            ivParameterSpec = new IvParameterSpec(iv.getBytes());
        }
        SymmetricCrypto crypto = new SymmetricCrypto(mode, secretKey, ivParameterSpec);
        return crypto.encryptHex(data);
    }

    /**
     * AES解密,使用默认模式 AES/CBC/PKCS5Padding,默认密钥和偏移量
     *
     * @param data 待解密数据
     * @return 解密后的数据
     */
    public static String decrypt(String data) {
        return decrypt(data, mode, key, iv);
    }

    /**
     * AES解密,使用AES/ECB/PKCS5Padding模式
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key) {
        return decrypt(data, "AES/ECB/PKCS5Padding", key, null);
    }

    /**
     * AES解密,使用默认模式 AES/CBC/PKCS5Padding
     *
     * @param data 待解密数据
     * @param key  密钥
     * @param iv   偏移量
     * @return 解密后的数据
     */
    public static String decrypt(String data, String key, String iv) {
        return decrypt(data, mode, key, iv);
    }

    /**
     * AES解密
     *
     * @param data 待解密数据
     * @return 解密后的数据
     */
    public static String decrypt(String data, String mode, String key, String iv) {
        return decrypt(data, mode, key.getBytes(), iv.getBytes());
    }


    /**
     * AES解密
     *
     * @param data 待解密数据
     * @param keys 密钥
     * @param iv   偏移量
     * @return 解密后的数据
     */
    public static String decrypt(String data, String mode, byte[] keys, byte[] iv) {
        SecretKey secretKey = KeyUtil.generateKey("AES", keys);
        IvParameterSpec ivParameterSpec = null;
        if (iv != null) {
            ivParameterSpec = new IvParameterSpec(iv);
        }
        SymmetricCrypto crypto = new SymmetricCrypto(mode, secretKey, ivParameterSpec);
        return crypto.decryptStr(data);
    }
}
