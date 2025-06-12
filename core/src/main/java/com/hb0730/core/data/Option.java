package com.hb0730.core.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.io.Serializable;
import java.util.Map;

/**
 * 选项
 *
 * @author <a href="mailto:huangbing0730@gmail">hb0730</a>
 * @date 2024/10/12
 */
@Data
@Schema(name = "选项")
public class Option implements Serializable {
    /**
     * 标签
     */
    private String label;
    /**
     * 值
     */
    private Object value;
    /**
     * 描述
     */
    private String description;
    /**
     * 其他属性
     */
    private Map<String, Object> attrs;
}
