package com.hb0730.desensitize.core.desensitization;

import com.hb0730.base.pool.RegexPool;
import com.hb0730.base.utils.DesensitizeUtil;
import jakarta.annotation.Nonnull;

/**
 * Email 脱敏
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public class EmailDesensitization extends StringDesensitization {
    @Override
    String doDesensitize(@Nonnull String original) {
        return apply(RegexPool.EMAIL, original, DesensitizeUtil::email);
    }
}
