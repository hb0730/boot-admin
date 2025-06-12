package com.hb0730.base;

import java.util.Optional;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
public interface PairEnum<K, T extends Pair<K, ?>> {
    /**
     * 代码名称对
     *
     * @return 代码名称对
     */
    T getValue();

    /**
     * 代码
     *
     * @return 代码
     */
    K getCode();

    /**
     * 名称
     *
     * @return 名称
     */
    String getMessage();

    /**
     * 通过代码获取枚举
     *
     * @param clazz 枚举类型
     * @param code  代码
     * @param <K>   代码类型
     * @param <T>   枚举类型
     * @return 枚举
     */
    static <K, T extends PairEnum<K, ?>> Optional<T> of(Class<T> clazz, K code) {
        //获取类型对应的枚举
        T[] enums = clazz.getEnumConstants();
        for (T anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return Optional.of(anEnum);
            }
        }
        return Optional.empty();
    }

    /**
     * 通过代码获取枚举
     *
     * @param clazz 枚举类型
     * @param code  代码
     * @param <K>   代码类型
     * @param <T>   枚举类型
     * @return 枚举
     */
    static <K, T extends PairEnum<K, ?>> T of(Class<T> clazz, K code, T defaultValue) {
        //获取类型对应的枚举
        T[] enums = clazz.getEnumConstants();
        for (T anEnum : enums) {
            if (anEnum.getCode().equals(code)) {
                return anEnum;
            }
        }
        return defaultValue;
    }

    /**
     * 通过名称获取枚举
     *
     * @param clazz   枚举类型
     * @param message 名称
     * @param <K>     代码类型
     * @param <T>     枚举类型
     * @return 枚举
     */
    static <K, T extends PairEnum<K, ?>> T ofMessage(Class<T> clazz, String message) {
        //获取类型对应的枚举
        T[] enums = clazz.getEnumConstants();
        for (T anEnum : enums) {
            if (anEnum.getMessage().equals(message)) {
                return anEnum;
            }
        }
        return null;
    }
}
