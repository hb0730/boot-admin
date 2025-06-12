package com.hb0730.mybatis.core.encrypt;


import com.hb0730.base.utils.AesCryptoUtil;
import com.hb0730.base.utils.StrUtil;

/**
 * AES 加密服务,使用AES/CBC/PKCS5Padding 加密算法
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public class AesMybatisEncryptService implements MybatisEncryptService {
    private final String key;
    private final String iv;

    public AesMybatisEncryptService(String key, String iv) {
        this.key = key;
        this.iv = iv;
    }

    @Override
    public String encrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return content;
        }
        return AesCryptoUtil.encrypt(content, key, iv);
    }

    @Override
    public String decrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return content;
        }
        return AesCryptoUtil.decrypt(content, key, iv);
    }
}
