package com.hb0730.desensitize.core.desensitization;

import com.hb0730.base.utils.DesensitizeUtil;
import jakarta.annotation.Nonnull;

/**
 * 中文名脱敏
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public class ChineseNameDesensitization extends StringDesensitization {
    @Override
    String doDesensitize(@Nonnull String original) {
        return apply(original, DesensitizeUtil::chineseName);
    }
}
