package com.hb0730.mybatis.core.encrypt;


import com.hb0730.base.utils.Base64Util;
import com.hb0730.base.utils.StrUtil;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/3
 */
public class Base64MybatisEncryptService implements MybatisEncryptService {
    @Override
    public String encrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return content;
        }
        return Base64Util.encode(content);

    }

    @Override
    public String decrypt(String content) {
        if (StrUtil.isBlank(content)) {
            return content;
        }
        return Base64Util.decodeStr(content);
    }
}
