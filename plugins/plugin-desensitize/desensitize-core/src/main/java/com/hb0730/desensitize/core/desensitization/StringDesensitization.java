package com.hb0730.desensitize.core.desensitization;

import com.hb0730.base.utils.StrUtil;
import jakarta.annotation.Nonnull;

/**
 * 脱敏 String
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public abstract class StringDesensitization implements Desensitization<String> {

    @Override
    public String desensitize(String original) {
        if (StrUtil.isBlank(original)) {
            return original;
        }
        return doDesensitize(original);
    }

    /**
     * 脱敏
     *
     * @param original 原始数据
     * @return 脱敏后的数据
     */
    abstract String doDesensitize(@Nonnull String original);
}
