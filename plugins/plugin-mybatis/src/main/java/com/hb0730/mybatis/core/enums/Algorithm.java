package com.hb0730.mybatis.core.enums;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/14
 */
public enum Algorithm {
    /**
     * AES,使用AES/CBC/PKCS5Padding
     */
    AES,
    /**
     * BASE64
     */
    BASE64,
    /**
     * 不加密
     */
    NON,
    /**
     * 全局-默认AES
     */
    GLOBAL,
    ;
}
