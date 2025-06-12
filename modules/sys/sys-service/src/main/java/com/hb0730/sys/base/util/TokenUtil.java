package com.hb0730.sys.base.util;

import cn.hutool.core.codec.Base62;
import cn.hutool.core.convert.Convert;
import com.hb0730.base.Pair;
import com.hb0730.base.utils.AesCryptoUtil;
import com.hb0730.base.utils.StrUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/9
 */
@Slf4j
public class TokenUtil {
    /**
     * 生成token
     *
     * @param id        用户id
     * @param loginTime 登录时间
     * @return token
     */
    public static String generateToken(String id, long loginTime) {
        // 返回token
        String encrypt = AesCryptoUtil.encrypt(id + ":" + loginTime);
        return Base62.encode(encrypt);
    }

    /**
     * 解析token
     *
     * @param token token
     * @return id, loginTime
     */
    public static Pair<String, Long> parseToken(String token) {
        try {
            if (StrUtil.isBlank(token)) {
                return null;
            }
            String decrypt = AesCryptoUtil.decrypt(Base62.decodeStr(token));
            String[] split = decrypt.split(":");
            return new Pair<>(split[0], Convert.toLong(split[1]));
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }
}
