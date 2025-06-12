package com.hb0730.mybatis.core.encrypt;

/**
 * 加密服务
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public interface MybatisEncryptService {
    /**
     * 加密算法
     *
     * @param content 内容
     * @return 加密后的内容
     */
    String encrypt(String content);

    /**
     * 解密算法
     *
     * @param content 内容
     * @return 解密后的内容
     */
    String decrypt(String content);
}
