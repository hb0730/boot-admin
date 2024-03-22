package com.hb0730.base.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * 处理结果详细信息
 *
 * @param <K> 处理结果代码
 * @param <V> 处理结果消息
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/1/23
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Pair<K, V> implements Serializable {
    /**
     * 处理结果代码
     */
    private K code;
    /**
     * 处理结果消息
     */
    private V message;

}
