package com.hb0730.base.enums;

import jakarta.annotation.Nullable;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/3/25
 */
public interface ValueEnum<T> {
    /**
     * 获取值
     *
     * @return 值
     */
    T getValue();

    /**
     * 通过值获取枚举
     *
     * @param enumType 枚举类型
     * @param value    值
     * @param <V>      值类型
     * @param <E>      枚举类型
     * @return 枚举
     */
    @Nullable
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        return Stream.of(enumType.getEnumConstants())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElse(null);
    }

    /**
     * 通过值获取枚举
     *
     * @param enumType 枚举类型
     * @param value    值
     * @param <V>      值类型
     * @param <E>      枚举类型
     * @return 枚举
     */
    @Nullable
    static <V, E extends ValueEnum<V>> E valueToEnum(Class<E> enumType, V value, E defaultValue) {
        return Stream.of(enumType.getEnumConstants())
                .filter(e -> e.getValue().equals(value))
                .findFirst()
                .orElse(defaultValue);
    }

    /**
     * 判断是否存在
     *
     * @param enumType 枚举类型
     * @param value    值
     * @param <V>      值类型
     * @param <E>      枚举类型
     * @return 是否存在
     */
    static <V, E extends ValueEnum<V>> boolean hash(Class<E> enumType, V value) {
        return Stream.of(enumType.getEnumConstants())
                .anyMatch(e -> e.getValue().equals(value));
    }
}