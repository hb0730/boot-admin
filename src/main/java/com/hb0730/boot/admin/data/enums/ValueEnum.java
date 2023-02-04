package com.hb0730.boot.admin.data.enums;

import java.util.stream.Stream;

/**
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2023/1/11
 */
public interface ValueEnum<T> {
    /**
     * Gets enum value.
     *
     * @return enum value
     */
    T getValue();

    /**
     * Converts value to corresponding enum.
     *
     * @param enumType 枚举类型,不为空
     * @param value    值
     * @param <V>      值类型
     * @param <E>      枚举类型
     * @return corresponding enum
     */
    static <V, E extends Enum<E> & ValueEnum<V>> E valueToEnum(Class<E> enumType, V value) {
        return Stream.of(enumType.getEnumConstants())
            .filter(item -> item.getValue().equals(value))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("unknown database value: " + value));
    }

    /**
     * 是否存在当前值
     *
     * @param enumType 枚举类型
     * @param value    值
     * @param <V>      值类型
     * @param <E>      枚举类型
     * @return 是否存在
     */
    static <V, E extends Enum<E> & ValueEnum<V>> boolean hash(Class<E> enumType, V value) {
        return Stream.of(enumType.getEnumConstants()).anyMatch(item -> item.getValue().equals(value));
    }
}
