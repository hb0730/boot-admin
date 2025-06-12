package com.hb0730.base.utils;

import cn.hutool.crypto.digest.MD5;

/**
 * md5工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */

public class Md5Util {

    /**
     * md5加密,16位
     *
     * @param data 字符串
     * @return 加密后的字符串
     */
    public static String md5Hex(String data) {
        return md5Hex(data, null, 1);
    }

    /**
     * md5加密,16位
     *
     * @param data 字符串
     * @param salt 盐
     * @return 加密后的字符串
     */
    public static String md5Hex(String data, String salt) {
        return md5Hex(data, salt, 1);
    }

    /**
     * md5加密,16位
     *
     * @param data           字符串
     * @param salt           盐
     * @param hashIterations 迭代次数
     * @return 加密后的字符串
     */
    public static String md5Hex(String data, String salt, int hashIterations) {
        MD5 md5 = null;
        if (StrUtil.isNotBlank(salt)) {
            md5 = new MD5(salt.getBytes(), hashIterations);
        } else {
            md5 = new MD5();
        }
        return md5.digestHex16(data);
    }
}
