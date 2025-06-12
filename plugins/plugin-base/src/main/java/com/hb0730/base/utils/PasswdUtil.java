package com.hb0730.base.utils;

import cn.hutool.core.util.RandomUtil;

/**
 * 密码工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
public class PasswdUtil {
    static final String BASE_CHAR_NUMBER_SPECIAL = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*()_+-=[]{};':\"\\|,.<>?";

    /**
     * 加密
     *
     * @param password 密码
     * @param salt     盐
     * @return 加密后的密码
     */
    public static String encrypt(String password, String salt) {
        return Md5Util.md5Hex(password, salt);
    }

    /**
     * 匹配
     *
     * @param password        密码
     * @param salt            盐
     * @param encryptPassword 加密后的密码
     * @return 是否匹配
     */
    public static boolean matches(String password, String salt, String encryptPassword) {
        return encrypt(password, salt).equals(encryptPassword);
    }

    /**
     * 生成盐
     *
     * @return 盐
     */
    public static String generateSalt() {
        return RandomUtil.randomString(6);
    }

    /**
     * 检查密码
     * <p>
     * 1. 长度大于等于6
     * 2. 包含数字
     * 3. 包含大小写字母
     * 4. 包含特殊字符
     * 5. 不包含空格
     *
     * @param password 密码
     * @return 是否符合
     */
    public static boolean checkPwd(String password) {
        return password.matches("^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{6,}$");
    }

    /**
     * 生成密码,包含大小写字母、数字、特殊字符
     *
     * @param length 长度
     * @return 密码
     * @see #generatePwd(int, int)
     */
    public static String generatePwd(int length) {
        //必须包含一个数字、一个大写字母、一个小写字母、一个特殊字符
        String pwd = RandomUtil.randomString(BASE_CHAR_NUMBER_SPECIAL, length);
        if (checkPwd(pwd)) {
            return pwd;
        }
        return generatePwd(length);
    }

    /**
     * 生成密码,包含大小写字母、数字、特殊字符
     *
     * @param length   长度
     * @param tryCount 尝试次数
     * @return 密码
     */
    public static String generatePwd(int length, int tryCount) {
        String pwd = RandomUtil.randomString(BASE_CHAR_NUMBER_SPECIAL, length);
        if (checkPwd(pwd)) {
            return pwd;
        }
        if (tryCount > 0) {
            return generatePwd(length, tryCount - 1);
        }
        return pwd;
    }
}
