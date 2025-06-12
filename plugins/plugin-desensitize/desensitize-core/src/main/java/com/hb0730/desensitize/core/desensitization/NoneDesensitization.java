package com.hb0730.desensitize.core.desensitization;

import jakarta.annotation.Nonnull;

/**
 * 不脱敏
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public class NoneDesensitization extends StringDesensitization {
    @Override
    String doDesensitize(@Nonnull String original) {
        return original;
    }
}
