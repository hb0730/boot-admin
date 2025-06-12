package com.hb0730.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 处理结果详细信息
 *
 * @param <K> 处理结果代码
 * @param <V> 处理结果消息
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/9/25
 */
@Getter
@Setter
public class Pair<K, V> implements Serializable {
    /**
     * 处理结果代码
     */
    private K code;
    /**
     * 处理结果消息
     */
    private V message;

    /**
     * 默认构造函数:反序列化用
     */
    public Pair() {
    }

    /**
     * 处理结果详细信息
     *
     * @param code    处理结果代码
     * @param message 处理结果消息
     */
    public Pair(K code, V message) {
        this.code = code;
        this.message = message;
    }

    /**
     * 创建处理结果详细信息
     *
     * @param code    处理结果代码
     * @param message 处理结果消息
     * @param <K>     处理结果代码
     * @param <V>     处理结果消息
     * @return 处理结果详细信息
     */
    public static <K, V> Pair<K, V> of(K code, V message) {
        return new Pair<>(code, message);
    }
}
