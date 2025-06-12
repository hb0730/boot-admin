package com.hb0730.operator.log.core.define;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * JSON 包装
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Wrapper<T> implements Serializable {
    private T value;
}
