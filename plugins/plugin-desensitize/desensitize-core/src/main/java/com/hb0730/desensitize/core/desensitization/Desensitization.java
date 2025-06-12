package com.hb0730.desensitize.core.desensitization;

import java.util.function.Function;
import java.util.regex.Pattern;

/**
 * 脱敏
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/10
 */
public interface Desensitization<T> {

    /**
     * 脱敏
     *
     * @param original 原始数据
     * @return 脱敏后的数据
     */
    T desensitize(T original);

    /**
     * 正则匹配脱敏
     *
     * @param regex    正则
     * @param original 原始数据
     * @param function 脱敏方法
     * @return 脱敏后的数据
     */
    default T apply(String regex, T original, Function<T, T> function) {
        return apply(Pattern.compile(regex), original, function);
    }

    /**
     * 正则匹配脱敏
     *
     * @param pattern  正则
     * @param original 原始数据
     * @param function 脱敏方法
     * @return 脱敏后的数据
     */
    default T apply(Pattern pattern, T original, Function<T, T> function) {
        if (pattern.matcher(original.toString()).matches()) {
            return function.apply(original);
        }
        return original;
    }

    /**
     * 脱敏
     *
     * @param original 原始数据
     * @param function 脱敏方法
     * @return 脱敏后的数据
     */
    default T apply(T original, Function<T, T> function) {
        return function.apply(original);
    }
}
