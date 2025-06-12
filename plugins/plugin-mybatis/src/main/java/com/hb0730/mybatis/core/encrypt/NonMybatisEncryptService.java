package com.hb0730.mybatis.core.encrypt;

/**
 * 不加密
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/18
 */
public class NonMybatisEncryptService implements MybatisEncryptService {
    @Override
    public String encrypt(String content) {
        return content;
    }

    @Override
    public String decrypt(String content) {
        return content;
    }
}
