package com.hb0730.base.utils;

/**
 * 脱敏工具类
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public class DesensitizeUtil {

    /**
     * [中文姓名] 只显示第一个汉字，其他隐藏为2个星号<例子：张**>
     *
     * @param fullName 姓名
     * @return 脱敏后的姓名
     */
    public static String chineseName(String fullName) {
        return mask(fullName, 1, fullName.length());
    }

    /**
     * [身份证号] 前三,后四位，其他隐藏。共计18位或者15位。<例子：420**********5762>
     *
     * @param idCard 身份证号
     * @return 脱敏后的身份证号
     */
    public static String idCard(String idCard) {
        return mask(idCard, 3, idCard.length() - 4);
    }

    /**
     * [固定电话] 后四位，其他隐藏<例子：****1234>
     *
     * @param phone 电话号码
     * @return 脱敏后的电话号码
     */
    public static String fixedPhone(String phone) {
        return mask(phone, 0, phone.length() - 4);
    }

    /**
     * [手机号码] 前三位，后四位，其他隐藏<例子:138******1234>
     *
     * @param mobile 手机号码
     * @return 脱敏后的手机号码
     */
    public static String mobile(String mobile) {
        return mask(mobile, 3, mobile.length() - 4);
    }

    /**
     * [地址] 只显示到地区，不显示详细地址；我们要对个人信息增强保护<例子：北京市海淀区****
     *
     * @param address       地址
     * @param sensitiveSize 敏感信息长度
     * @return 脱敏后的地址
     */
    public static String address(String address, int sensitiveSize) {
        return maskRight(address, sensitiveSize);
    }

    /**
     * [电子邮箱] 邮箱前缀仅显示第一个字母，前缀其他隐藏，用星号代替，@及后面的地址显示<例子:g**@163.com>
     *
     * @param email 电子邮箱
     * @return 脱敏后的邮箱
     */
    public static String email(String email) {
        if (email == null || email.isEmpty()) {
            return email;
        }
        int index = email.indexOf("@");
        if (index <= 1) {
            return email;
        }
        return mask(email, 1, index);
    }

    /**
     * [银行卡号] 前六位，后四位，其他用星号隐藏每位1个星号<例子:6222600**********1234>
     */
    public static String bankCard(final String cardNum) {
        return mask(cardNum, 6, cardNum.length() - 4);
    }

    /**
     * [api秘钥] 前3位，后3位，其他用星号隐藏每位1个星号<例子:Aj3**********8Kl>
     *
     * @param secret api秘钥
     * @return 脱敏后的秘钥
     */
    public static String apiSecret(String secret) {
        return mask(secret, 3, secret.length() - 3);
    }

    /**
     * 脱敏,隐藏部分字符,用*代替
     *
     * @param str   需要脱敏的字符串
     * @param start 开始位置
     * @param end   结束位置
     * @return 脱敏后的字符串
     */
    public static String mask(String str, int start, int end) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (start < 0 || end < 0) {
            return str;
        }
        if (start >= end) {
            return str;
        }
        if (end > str.length()) {
            end = str.length();
        }
        if (start >= str.length()) {
            return str;
        }

        if (start == 0 && end == str.length()) {
            return "*".repeat(str.length());
        }
        int maskLength = end - start;
        return str.substring(0, start) + "*".repeat(maskLength) + str.substring(end);
    }

    /**
     * 从右向左脱敏
     *
     * @param str    需要脱敏的字符串
     * @param length 需要脱敏的长度
     * @return 脱敏后的字符串
     */
    public static String maskRight(String str, int length) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (length <= 0) {
            return str;
        }
        int len = str.length();
        if (length >= len) {
            return str;
        }
        return mask(str, len - length, len);
    }

    /**
     * 从左向右脱敏
     *
     * @param str    需要脱敏的字符串
     * @param length 需要脱敏的长度
     * @return 脱敏后的字符串
     */
    public static String maskLeft(String str, int length) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        if (length <= 0) {
            return str;
        }
        int len = str.length();
        if (length >= len) {
            return str;
        }
        return mask(str, 0, length);
    }
}
